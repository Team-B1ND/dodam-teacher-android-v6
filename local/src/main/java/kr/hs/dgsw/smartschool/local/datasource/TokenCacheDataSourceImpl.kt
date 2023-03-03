package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.token.TokenCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class TokenCacheDataSourceImpl @Inject constructor(
    private val tokenDao: TokenDao,
) : TokenCacheDataSource {

    override suspend fun getToken(): Token =
        tokenDao.getToken().toModel()

    override suspend fun deleteToken() =
        tokenDao.deleteToken()

    override suspend fun insertToken(token: Token) =
        tokenDao.insert(token.toEntity())
}
