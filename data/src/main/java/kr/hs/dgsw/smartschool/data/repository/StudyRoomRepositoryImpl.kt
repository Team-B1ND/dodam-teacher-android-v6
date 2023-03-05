package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
    override val cache : Any
) : BaseRepository<StudyRoomRemoteDataSource, Any>,StudyRoomRepository {

    override suspend fun getHistoryByTime(startTime: String, endTime: String): StudyRoomList {
        return StudyRoomList(
            remote.getAllHistory().studyRoomList.filter {
                it.timeTable.startTime == startTime && it.timeTable.endTime == endTime
            }
        )
    }

    override suspend fun getHistoryById(id: Int): StudyRoomList {
        return remote.getHistoryById(id)
    }

    override suspend fun checkStudyRoom(id: Int, isChecked: Boolean) {
        when(isChecked){
            true -> remote.unCheckStudyRoom(id)
            false -> remote.checkStudyRoom(id)
        }
    }

    override suspend fun ctrlStudyRoom(studentId: Int, studyRoomList: StudyRoomRequest) {
        remote.ctrlStudyRoom(studentId, studyRoomList)
    }
}