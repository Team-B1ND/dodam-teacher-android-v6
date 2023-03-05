package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.StudyRoomService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class StudyRoomRemoteDataSourceImpl @Inject constructor(
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

    override suspend fun checkStudyRoom(
        id : Int
    ) = dodamApiCall {
        studyRoomService.postCheckStudyRoom(id).data
    }

    override suspend fun unCheckStudyRoom(
        id : Int
    ) = dodamApiCall {
        studyRoomService.postUnCheckStudyRoom(id).data
    }

    override suspend fun ctrlStudyRoom(
        studentId : Int, studyRoomList: StudyRoomRequest
    ) = dodamApiCall {
        studyRoomService.postStudyRoomCtrl(studentId, studyRoomList).data
    }
}