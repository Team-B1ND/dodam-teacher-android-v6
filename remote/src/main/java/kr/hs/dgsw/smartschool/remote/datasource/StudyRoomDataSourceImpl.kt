package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.StudyRoomService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class StudyRoomDataSourceImpl @Inject constructor(
    private val studyRoomService: StudyRoomService
) : StudyRoomRemoteDataSource {
    override suspend fun getAllHistory(
    ) : StudyRoomList = dodamApiCall {
        studyRoomService.getAllHistory().data.toModel()
    }

    override suspend fun getHistoryById(
        id : Int
    ) : StudyRoomList = dodamApiCall {
        studyRoomService.getHistoryById(id).data.toModel()
    }

    override suspend fun checkStudyRoom(id : Int) {

    }

    override suspend fun unCheckStudyRoom(id : Int) {

    }

    override suspend fun ctrlStudyRoom(studentId : Int, placeId : Int, timeTableId : Int) {

    }
}