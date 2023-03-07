package kr.hs.dgsw.smartschool.data.repository

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.exception.UnknownException
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository

class ClassroomRepositoryImpl @Inject constructor(
    override val remote: ClassroomRemoteDataSource,
    override val cache: ClassroomCacheDataSource
) : BaseRepository<ClassroomRemoteDataSource, ClassroomCacheDataSource>, ClassroomRepository {

    override suspend fun setClassrooms(): List<Classroom> =
        cache.deleteAllClassroom().let {
            remote.getClassrooms().apply {
                cache.insertClassrooms(this)
            }
        }

    override suspend fun getClassrooms(): List<Classroom> =
        cache.getAllClassroom().ifEmpty {
            remote.getClassrooms().apply {
                cache.insertClassrooms(this)
            }
        }

    override suspend fun getClassroomById(id: Int): Classroom =
        cache.getClassroomById(id).let {
            it ?: remote.getClassrooms().let { classrooms ->
                cache.insertClassrooms(classrooms)
                classrooms.find { classroom ->  classroom.id == id } ?: throw UnknownException("교실을 받아오지 못했어요")
            }
        }

    override suspend fun getClassroomByStudentId(id: Int): Classroom =
        cache.getClassroomByPlaceId(id).let {
            it ?: remote.getClassrooms().let { classrooms ->
                cache.insertClassrooms(classrooms)
                classrooms.find { classroom -> classroom.place.id == id } ?: throw UnknownException("교실을 받아오지 못했어요")
            }
        }
}
