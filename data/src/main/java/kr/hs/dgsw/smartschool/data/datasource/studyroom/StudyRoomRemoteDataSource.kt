package kr.hs.dgsw.smartschool.data.datasource.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem

interface StudyRoomRemoteDataSource {
    suspend fun getStudyRooms(
        year: Int,
        month: Int,
        day: Int
    ): List<StudyRoom>

    suspend fun checkStudyRoom(id: Int)

    suspend fun unCheckStudyRoom(id: Int)

    suspend fun ctrlStudyRoom(studentId: Int, studyRoomList: List<StudyRoomItem>)
}
