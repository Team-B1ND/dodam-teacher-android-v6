package kr.hs.dgsw.smartschool.data.datasource.auth

import kr.hs.dgsw.smartschool.domain.model.account.Account
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.token.Token

interface AuthCacheDataSource {

    suspend fun logout()

    suspend fun insertMember(member: Member)

    suspend fun insertToken(token: Token)

    suspend fun getAccount(): Account

    suspend fun insertAccount(id: String, pw: String)
}
