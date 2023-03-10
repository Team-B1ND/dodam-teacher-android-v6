package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.domain.exception.UnknownException
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    override val remote: MemberRemoteDataSource,
    override val cache: StudentCacheDataSource
) : BaseRepository<MemberRemoteDataSource, StudentCacheDataSource>, StudentRepository {

    private val NOT_FOUND_STUDENT_MESSAGE = "해당 학생을 찾을 수 없어요"

    override suspend fun setStudents(): List<Student> =
        remote.getStudents().apply {
            updateStudents(this)
        }

    override suspend fun getStudents(): List<Student> =
        cache.getStudents().ifEmpty {
            remote.getStudents().apply {
                updateStudents(this)
            }
        }

    override suspend fun getStudentById(id: Int): Student =
        cache.getStudentById(id) ?: remote.getStudents().let { students ->
            updateStudents(students)
            students.find { it.id == id } ?: throw UnknownException(NOT_FOUND_STUDENT_MESSAGE)
        }

    override suspend fun getStudentByMemberId(id: String): Student =
        cache.getStudentByMemberId(id) ?: remote.getStudents().let { students ->
            updateStudents(students)
            students.find { it.member.id == id } ?: throw UnknownException(NOT_FOUND_STUDENT_MESSAGE)
        }

    private suspend fun updateStudents(students: List<Student>) {
        cache.deleteAllStudent().apply {
            cache.insertStudents(students)
        }
    }
}
