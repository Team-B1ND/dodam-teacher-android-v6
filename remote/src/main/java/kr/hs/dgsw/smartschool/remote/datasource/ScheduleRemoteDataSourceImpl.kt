package kr.hs.dgsw.smartschool.remote.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.ScheduleService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleService: ScheduleService,
) : ScheduleRemoteDataSource {

    override suspend fun getSchedules(startDate: String, endDate: String): List<Schedule> = dodamApiCall {
        scheduleService.getSchedules(startDate, endDate).data.toModel()
    }
}
