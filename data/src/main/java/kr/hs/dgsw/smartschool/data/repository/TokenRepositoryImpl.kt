package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.token.TokenCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    override val remote: TokenRemoteDataSource,
    override val cache: TokenCacheDataSource,
) : BaseRepository<TokenRemoteDataSource, TokenCacheDataSource>, TokenRepository {

    override suspend fun getToken(): Token =
        cache.getToken()

    override suspend fun fetchToken(): Token =
        cache.getToken().refreshToken.let { refreshToken ->
            remote.getToken(refreshToken).let { accessToken ->
                cache.insertToken(
                    Token(
                        token = accessToken,
                        refreshToken = refreshToken
                    )
                ).let {
                    Token(accessToken, refreshToken)
                }
            }
        }
}
