package kr.hs.dgsw.smartschool.data.datasource.student

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

interface StudentCacheDataSource {

    suspend fun getStudents(): List<Student>

    suspend fun getStudentById(id: Int): Student?

    suspend fun getStudentByMemberId(id: String): Student?

    suspend fun deleteAllStudent()

    suspend fun insertStudent(student: Student)

    suspend fun insertStudents(students: List<Student>)
}
