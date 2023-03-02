package kr.hs.dgsw.smartschool.remote.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.remote.service.TokenService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall

class TokenRemoteDataSourceImpl @Inject constructor(
    private val tokenService: TokenService,
) : TokenRemoteDataSource {

    override suspend fun getToken(token: String): String = dodamApiCall {
        tokenService.getToken(token).data
    }
}
