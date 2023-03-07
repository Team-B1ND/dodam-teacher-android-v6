package kr.hs.dgsw.smartschool.data.datasource.teacher

import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

interface TeacherCacheDataSource {

    suspend fun getTeachers(): List<Teacher>

    suspend fun getTeacherById(id: Int): Teacher?

    suspend fun getTeacherByMemberId(id: String): Teacher?

    suspend fun deleteAllTeacher()

    suspend fun insertTeacher(teacher: Teacher)

    suspend fun insertTeachers(teachers: List<Teacher>)
}