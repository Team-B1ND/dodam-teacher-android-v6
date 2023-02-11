package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.local.dao.AccountDao
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.entity.account.AccountEntity
import kr.hs.dgsw.smartschool.local.mapper.toEntity

class AuthCacheDataSourceImpl @Inject constructor(
    private val memberDao: MemberDao,
    private val tokenDao: TokenDao,
    private val accountDao: AccountDao,
) : AuthCacheDataSource {

    override suspend fun logout() {
        tokenDao.deleteToken()
    }

    override suspend fun insertMember(member: Member) {
        memberDao.insert(member.toEntity())
    }

    override suspend fun insertToken(token: Token) {
        tokenDao.insert(token.toEntity())
    }

    override suspend fun insertAccount(id: String, pw: String) {
        accountDao.insert(AccountEntity(id, pw))
    }
}
