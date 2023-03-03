package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.remote.service.TokenService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class TokenRemoteDataSourceImpl @Inject constructor(
    private val tokenService: TokenService,
) : TokenRemoteDataSource {

    override suspend fun getToken(token: String): String = dodamApiCall {
        tokenService.getToken(token).data
    }
}
