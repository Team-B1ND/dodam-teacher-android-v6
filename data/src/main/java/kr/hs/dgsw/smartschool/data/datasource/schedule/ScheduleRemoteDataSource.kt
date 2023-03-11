package kr.hs.dgsw.smartschool.data.datasource.schedule

import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule

interface ScheduleRemoteDataSource {

    suspend fun getSchedules(
        startDate: String,
        endDate: String,
    ): List<Schedule>
}
