package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest

interface StudyRoomRepository {
    suspend fun getHistoryByTime(startTime : String, endTime : String) : StudyRoomList

    suspend fun getHistoryById(id : Int) : StudyRoomList

    suspend fun checkStudyRoom(id : Int)

    suspend fun unCheckStudyRoom(id : Int)

    suspend fun ctrlStudyRoom(studentId : Int, studyRoomList: StudyRoomRequest)
}