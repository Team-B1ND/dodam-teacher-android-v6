package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest

interface StudyRoomRepository {

    suspend fun getAllSheet(): StudyRoomList
    suspend fun getSheetByTime(startTime : String, endTime : String) : StudyRoomList

    suspend fun getSheetById(id : Int) : StudyRoomList

    suspend fun checkStudyRoom(id : Int, isChecked : Boolean)

    suspend fun ctrlStudyRoom(studentId : Int, studyRoomList: StudyRoomRequest)
}