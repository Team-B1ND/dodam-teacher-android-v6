package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.local.dao.StudentDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class StudentCacheDataSourceImpl @Inject constructor(
    private val studentDao: StudentDao,
) : StudentCacheDataSource {

    override suspend fun getStudents(): List<Student> =
        studentDao.getAllStudent().toModel()

    override suspend fun getStudentById(id: Int): Student? =
        studentDao.getStudentById(id)?.toModel()

    override suspend fun getStudentByMemberId(id: String): Student? =
        studentDao.getStudentByMemberId(id)?.toModel()

    override suspend fun deleteAllStudent() =
        studentDao.deleteAllStudent()

    override suspend fun insertStudent(student: Student) =
        studentDao.insert(student.toEntity())

    override suspend fun insertStudents(students: List<Student>) {
        studentDao.insert(students.toEntity())
    }
}
