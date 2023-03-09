package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.local.dao.ClassroomDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class ClassroomCacheDataSourceImpl @Inject constructor(
    private val classroomDao: ClassroomDao,
) : ClassroomCacheDataSource {

    override suspend fun getAllClassroom(): List<Classroom> =
        classroomDao.getAllClassroom().toModel()

    override suspend fun getClassroomById(id: Int): Classroom? =
        classroomDao.getClassroomById(id)?.toModel()

    override suspend fun getClassroomByPlaceId(id: Int): Classroom? =
        classroomDao.getClassroomByPlaceId(id)?.toModel()

    override suspend fun deleteAllClassroom() =
        classroomDao.deleteAllClassroom()

    override suspend fun insertClassroom(classroom: Classroom) =
        classroomDao.insert(classroom.toEntity())

    override suspend fun insertClassrooms(classrooms: List<Classroom>) =
        classroomDao.insert(classrooms.toEntity())
}
