package kr.hs.dgsw.smartschool.data.datasource.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

interface StudyRoomCacheDataSource {

    suspend fun getStudyRooms(): List<StudyRoom>

    suspend fun deleteStudyRooms()

    suspend fun insertStudyRoom(studyRoom: StudyRoom)

    suspend fun insertStudyRooms(studyRooms: List<StudyRoom>)
}
