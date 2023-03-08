package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
) : BaseRepository<StudyRoomRemoteDataSource, Any>,StudyRoomRepository {
    override suspend fun getAllSheet()
    : StudyRoomList
    = remote.getAllSheet()

    override suspend fun getSheetByTime(startTime: String, endTime: String): StudyRoomList {
        return StudyRoomList(
            remote.getAllSheet().studyRoomList.filter {
                it.timeTable.startTime == startTime && it.timeTable.endTime == endTime
            }
        )
    }

    override suspend fun getSheetById(id: Int): StudyRoomList {
        return remote.getSheetById(id)
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

    override val cache: Any
        get() = TODO("Not yet implemented")
}