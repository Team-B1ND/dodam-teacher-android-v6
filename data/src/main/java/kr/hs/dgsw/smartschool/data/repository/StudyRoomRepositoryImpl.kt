package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
    override val cache : Any
) : BaseRepository<StudyRoomRemoteDataSource, Any>,StudyRoomRepository {
    override suspend fun getAllHistory(): StudyRoomList {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryById(id: Int): StudyRoomList {
        TODO("Not yet implemented")
    }

    override suspend fun checkStudyRoom(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun unCheckStudyRoom(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun ctrlStudyRoom(studentId: Int, placeId: Int, timeTableId: Int) {
        TODO("Not yet implemented")
    }
}