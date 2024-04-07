package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.Member

interface TeacherRepository {

//    suspend fun setTeachers(): List<Teacher>
//
//    suspend fun getTeachers(): List<Teacher>

    suspend fun getMyInfo(): Member

//    suspend fun getTeacherById(id: Int): Teacher

//    suspend fun getTeacherByMemberId(id: String): Teacher
}
