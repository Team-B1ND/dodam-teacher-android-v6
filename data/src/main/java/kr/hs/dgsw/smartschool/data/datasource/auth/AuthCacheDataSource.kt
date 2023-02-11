package kr.hs.dgsw.smartschool.data.datasource.auth

import kr.hs.dgsw.smartschool.domain.model.member.Member

interface AuthCacheDataSource {

    suspend fun logout()

    suspend fun insertMember(member: Member)
}