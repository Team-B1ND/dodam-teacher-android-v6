package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

interface TeacherRepository {

    suspend fun setTeachers(): List<Teacher>

    suspend fun getTeachers(): List<Teacher>

    suspend fun getMyInfo(): Teacher

    suspend fun getTeacherById(id: Int): Teacher

    suspend fun getTeacherByMemberId(id: String): Teacher
}
