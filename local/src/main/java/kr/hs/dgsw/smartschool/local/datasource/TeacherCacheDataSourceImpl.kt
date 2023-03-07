package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.teacher.TeacherCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.local.dao.TeacherDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel

class TeacherCacheDataSourceImpl @Inject constructor(
    private val teacherDao: TeacherDao,
) : TeacherCacheDataSource {

    override suspend fun getTeachers(): List<Teacher> =
        teacherDao.getAllTeacher().toModel()

    override suspend fun getTeacherById(id: Int): Teacher? =
        teacherDao.getTeacherById(id)?.toModel()

    override suspend fun getTeacherByMemberId(id: String): Teacher? =
        teacherDao.getTeacherByMemberId(id)?.toModel()

    override suspend fun deleteAllTeacher() =
        teacherDao.deleteAllTeacher()

    override suspend fun insertTeacher(teacher: Teacher) =
        teacherDao.insert(teacher.toEntity())

    override suspend fun insertTeachers(teachers: List<Teacher>) =
        teacherDao.insert(teachers.toEntity())
}
