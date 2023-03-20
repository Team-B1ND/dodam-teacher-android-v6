package kr.hs.dgsw.smartschool.data.repository

import java.time.LocalDate
import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
    override val cache: StudyRoomCacheDataSource
) : BaseRepository<StudyRoomRemoteDataSource, StudyRoomCacheDataSource>, StudyRoomRepository {

    override suspend fun setStudyRoom(year: Int, month: Int, day: Int): List<StudyRoom> =
        remote.getStudyRooms(year, month, day).apply {
            cache.deleteStudyRooms()
            cache.insertStudyRooms(this)
        }

    override suspend fun getStudyRooms(): List<StudyRoom> =
        cache.getStudyRooms().ifEmpty {
            val today = LocalDate.now()
            remote.getStudyRooms(today.year, today.monthValue, today.dayOfMonth).apply {
                cache.deleteStudyRooms()
                cache.insertStudyRooms(this)
            }
        }

    override suspend fun checkStudyRoom(id: Int) =
        remote.checkStudyRoom(id)

    override suspend fun unCheckStudyRoom(id: Int) =
        remote.unCheckStudyRoom(id)

    override suspend fun ctrlStudyRoom(studentId: Int, studyRoomList: List<StudyRoomItem>) =
        remote.ctrlStudyRoom(
            studentId = studentId,
            studyRoomList = studyRoomList,
        )
}
