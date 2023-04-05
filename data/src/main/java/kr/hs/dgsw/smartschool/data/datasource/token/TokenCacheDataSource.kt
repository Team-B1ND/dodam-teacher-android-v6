package kr.hs.dgsw.smartschool.data.datasource.token

import kr.hs.dgsw.smartschool.domain.model.token.Token

interface TokenCacheDataSource {

    suspend fun getToken(): Token

    suspend fun deleteToken()

    suspend fun insertToken(token: Token)
}
