package kr.hs.dgsw.smartschool.data.datasource.member

import kr.hs.dgsw.smartschool.domain.model.member.Member

interface MemberCacheDataSource {

    suspend fun getMembers(): List<Member>

    suspend fun getMemberById(id: String): Member?

    suspend fun getMemberByTeacherId(id: Int): Member?

    suspend fun getMemberByStudentId(id: Int): Member?

    suspend fun deleteAllMember()

    suspend fun insertMember(member: Member)

    suspend fun insertMembers(members: List<Member>)
}