package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.local.dao.StudyRoomDao
import kr.hs.dgsw.smartschool.local.mapper.toStudyRoomEntity
import kr.hs.dgsw.smartschool.local.mapper.toStudyRoomEntityList
import kr.hs.dgsw.smartschool.local.mapper.toStudyRoomList

class StudyRoomCacheDataSourceImpl @Inject constructor(
    private val studyRoomDao: StudyRoomDao,
) : StudyRoomCacheDataSource {

    override suspend fun getStudyRooms(): List<StudyRoom> =
        studyRoomDao.getStudyRooms().toStudyRoomList()

    override suspend fun deleteStudyRooms() =
        studyRoomDao.deleteStudyRooms()

    override suspend fun insertStudyRoom(studyRoom: StudyRoom) =
        studyRoomDao.insert(studyRoom.toStudyRoomEntity())

    override suspend fun insertStudyRooms(studyRooms: List<StudyRoom>) =
        studyRoomDao.insert(studyRooms.toStudyRoomEntityList())
}
