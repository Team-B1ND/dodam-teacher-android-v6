package kr.hs.dgsw.smartschool.remote.interceptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.domain.exception.ExpiredRefreshTokenException
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.FetchTokenUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.GetTokenUseCase
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val fetchTokenUseCase: FetchTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val authCacheDataSource: AuthCacheDataSource,
) : Interceptor {

    private val TOKEN_HEADER = "Authorization"
    private val TOKEN_ERROR = 401

    private lateinit var token: Token

    override fun intercept(chain: Interceptor.Chain): Response {

        runBlocking(Dispatchers.IO) {
            getTokenUseCase().onSuccess {
                token = it
            }.onFailure {
                throw ExpiredRefreshTokenException()
            }
        }

        val request: Request = chain.request().newBuilder()
            .addHeader(TOKEN_HEADER, "Bearer ${token.token}")
            // .addHeader(TOKEN_HEADER, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJJZCI6ImFkbWluIiwiYWNjZXNzTGV2ZWwiOjQsImFwaUtleUFjY2Vzc0xldmVsIjowLCJpYXQiOjE2Nzc1Njg1ODIsImV4cCI6MTY3ODAwMDU4MiwiaXNzIjoiZG9kYW0uY29tIiwic3ViIjoidG9rZW4ifQ.HndrLi_Wj-bz8Fis39Jks7rRA84hoWMlA-rHTmhb9y4")
            .build()

        var response = chain.proceed(request)
        if (response.code == TOKEN_ERROR) {
            response.close()
            runBlocking(Dispatchers.IO) {
                fetchTokenUseCase().onSuccess {
                    val refreshRequest: Request = chain.request().newBuilder()
                        .addHeader(TOKEN_HEADER, "Bearer ${it.token}")
                        // .addHeader(TOKEN_HEADER, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJJZCI6ImFkbWluIiwiYWNjZXNzTGV2ZWwiOjQsImFwaUtleUFjY2Vzc0xldmVsIjowLCJpYXQiOjE2Nzc1Njg1ODIsImV4cCI6MTY3ODAwMDU4MiwiaXNzIjoiZG9kYW0uY29tIiwic3ViIjoidG9rZW4ifQ.HndrLi_Wj-bz8Fis39Jks7rRA84hoWMlA-rHTmhb9y4")
                        .build()
                    response = chain.proceed(refreshRequest)

                    if (response.code == TOKEN_ERROR) {
                        response.close()
                        runBlocking(Dispatchers.IO) {
                            val account = authCacheDataSource.getAccount()
                            if (account.id == null && account.pw == null) {
                                response = Response.Builder()
                                    .request(request)
                                    .protocol(Protocol.HTTP_1_1)
                                    .code(TOKEN_ERROR)
                                    .message("세션이 만료되었습니다.")
                                    .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                                    .build()
                            } else {
                                loginUseCase(
                                    LoginUseCase.Param(
                                        id = account.id ?: "",
                                        pw = account.pw ?: "",
                                        enableAutoLogin = true,
                                    )
                                ).onSuccess { loginToken ->
                                    val loginRequest: Request = chain.request().newBuilder()
                                        .addHeader(TOKEN_HEADER, "Bearer ${loginToken.token}")
                                        .build()
                                    response = chain.proceed(loginRequest)

                                    if (response.code == TOKEN_ERROR) {
                                        throw ExpiredRefreshTokenException()
                                    }
                                }.onFailure {
                                    response = Response.Builder()
                                        .request(request)
                                        .protocol(Protocol.HTTP_1_1)
                                        .code(TOKEN_ERROR)
                                        .message("세션이 만료되었습니다.")
                                        .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                                        .build()
                                }
                            }
                        }
                    }
                }.onFailure {
                    response = Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(TOKEN_ERROR)
                        .message("세션이 만료되었습니다.")
                        .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                        .build()
                }
            }
        }

        return response
    }
}
