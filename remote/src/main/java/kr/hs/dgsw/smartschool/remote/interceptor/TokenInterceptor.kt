package kr.hs.dgsw.smartschool.remote.interceptor

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.smartschool.domain.exception.ExpiredRefreshTokenException
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.usecase.token.FetchTokenUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.GetTokenUseCase
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val fetchTokenUseCase: FetchTokenUseCase,
) : Interceptor {

    private val TOKEN_HEADER = "Authorization"

    private val postMethod = "POST"

    private val emptyTokenRequest = listOf(
        DodamUrl.Auth.LOGIN,
        DodamUrl.Auth.JOIN,
    )

    private lateinit var token: Token

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val method = request.method

        if (method != postMethod && emptyTokenRequest.contains(request.url.encodedPath).not()) {
            runBlocking(Dispatchers.IO) {
                getTokenUseCase().onSuccess {
                    token = it
                }.onFailure {
                    throw ExpiredRefreshTokenException()
                }
            }
            request = chain.request().newBuilder()
                .addHeader(TOKEN_HEADER, "Bearer ${token.token}")
                .build()
        }

        return chain.proceed(request)
    }
}