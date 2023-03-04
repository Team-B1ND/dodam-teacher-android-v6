package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList

interface StudyRoomRepository {
    suspend fun getAllHistory() : StudyRoomList

    suspend fun getHistoryById(id : Int) : StudyRoomList

    suspend fun checkStudyRoom(id : Int)

    suspend fun unCheckStudyRoom(id : Int)

    suspend fun ctrlStudyRoom(studentId : Int, placeId : Int, timeTableId : Int)
}