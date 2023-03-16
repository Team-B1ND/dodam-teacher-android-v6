package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.repository.TimeTableRepository
import java.time.LocalDate
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor(
    override val remote: TimeTableRemoteDataSource,
) : BaseRepository<TimeTableRemoteDataSource, Any>,TimeTableRepository {

    override val cache: Any
        get() = TODO("Not yet implemented")

    override suspend fun getTimeTables(): List<TimeTable> {
        return remote.getTimeTables()
    }
}