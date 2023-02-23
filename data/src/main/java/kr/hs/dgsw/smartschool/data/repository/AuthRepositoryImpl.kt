package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthRemoteDataSource
import kr.hs.dgsw.smartschool.data.utils.encryptSHA512
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    override val remote: AuthRemoteDataSource,
    override val cache: AuthCacheDataSource
) : BaseRepository<AuthRemoteDataSource, AuthCacheDataSource>, AuthRepository {

    override suspend fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    ) {
        remote.join(
            email = email,
            id = id,
            name = name,
            phone = phone,
            position = position,
            pw = pw.encryptSHA512(),
            tel = tel
        )
    }

    override suspend fun login(id: String, pw: String, enableAutoLogin: Boolean): Token {
        val password = pw.encryptSHA512()
        return remote.login(id, password).let {
            cache.insertMember(it.member)
            cache.insertToken(it.token)
            if (enableAutoLogin) {
                cache.insertAccount(id, password)
            }
            it.token
        }
    }

    override suspend fun getIsAutoLogin(): Boolean {
        val account = cache.getAccount()
        return (account.id != null) && (account.pw != null)
    }

    override suspend fun logout() {
        cache.logout()
    }
}
