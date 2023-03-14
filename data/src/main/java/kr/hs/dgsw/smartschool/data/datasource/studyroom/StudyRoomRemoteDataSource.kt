package kr.hs.dgsw.smartschool.data.datasource.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest

interface StudyRoomRemoteDataSource {
    suspend fun getAllSheet(
        year : Int,
        month : Int,
        day : Int
    ) : StudyRoomList

    suspend fun checkStudyRoom(id : Int)

    suspend fun unCheckStudyRoom(id : Int)

    suspend fun ctrlStudyRoom(studentId : Int, studyRoomList: StudyRoomRequest)
}