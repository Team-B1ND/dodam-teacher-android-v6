package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest

interface StudyRoomRepository {

    suspend fun getAllSheet(): List<StudyRoom>
    suspend fun getSheetByTime(startTime : String, endTime : String) :  List<StudyRoom>

    suspend fun getSheetByUserId(studentId: Int) :  List<StudyRoom>

    suspend fun checkStudyRoom(id : Int, isChecked : Boolean)

    suspend fun ctrlStudyRoom(studentId : Int, studyRoomList: StudyRoomRequest)
}