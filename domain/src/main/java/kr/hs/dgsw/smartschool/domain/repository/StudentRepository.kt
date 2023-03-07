package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

interface StudentRepository {

    suspend fun setStudents(): List<Student>

    suspend fun getStudents(): List<Student>

    suspend fun getStudentById(id: Int): Student

    suspend fun getStudentByMemberId(id: String): Student
}
