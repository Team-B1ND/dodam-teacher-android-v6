package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity

class AuthCacheDataSourceImpl @Inject constructor(
    private val memberDao: MemberDao,
    private val tokenDao: TokenDao,
) : AuthCacheDataSource {

    override suspend fun logout() {
        tokenDao.deleteToken()
    }

    override suspend fun insertMember(member: Member) {
        memberDao.insert(member.toEntity())
    }
}
