package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.TimeTableService
import javax.inject.Inject

class TimeTableRemoteDataSourceImpl @Inject constructor(
    private val timeTableService: TimeTableService
) : TimeTableRemoteDataSource {
    override suspend fun getTimeTables(): List<TimeTable> {
        return timeTableService.getTimeTables().data.toModel()
    }
}
