package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Member

interface MemberRepository {

    suspend fun setMembers(): List<Member>

    suspend fun getMembers(): List<Member>

    suspend fun getMemberById(id: String): Member

    suspend fun getMemberInfo(): Member

//    suspend fun getMemberByTeacherId(id: Int): Member
//
//    suspend fun getMemberByStudentId(id: Int): Member
}
