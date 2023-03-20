package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.request.studyroom.RequestItem
import kr.hs.dgsw.smartschool.remote.request.studyroom.StudyRoomCtrlRequest
import kr.hs.dgsw.smartschool.remote.service.StudyRoomService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem

class StudyRoomRemoteDataSourceImpl @Inject constructor(
    private val studyRoomService: StudyRoomService
) : StudyRoomRemoteDataSource {
    override suspend fun getStudyRooms(
        year: Int,
        month: Int,
        day: Int
    ): List<StudyRoom> = dodamApiCall {
        studyRoomService.getStudyRooms(year, month, day).data.toModel()
    }

    override suspend fun checkStudyRoom(
        id: Int
    ) = dodamApiCall {
        studyRoomService.postCheckStudyRoom(id).data
    }

    override suspend fun unCheckStudyRoom(
        id: Int
    ) = dodamApiCall {
        studyRoomService.postUnCheckStudyRoom(id).data
    }

    override suspend fun ctrlStudyRoom(
        studentId: Int,
        studyRoomList: List<StudyRoomItem>
    ) = dodamApiCall {
        studyRoomService.postStudyRoomCtrl(
            StudyRoomCtrlRequest(
                studentId = studentId,
                studyRoomList = studyRoomList.map {
                    RequestItem(it.placeId, it.timeTableId)
                }
            )
        ).data
    }
}
