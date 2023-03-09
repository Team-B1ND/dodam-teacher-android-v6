package kr.hs.dgsw.smartschool.data.datasource.schedule

import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule

interface ScheduleCacheDataSource {

    suspend fun getSchedules(startYear: Int, startMonth: Int): List<Schedule>

    suspend fun getSchedule(id: Int): Schedule?

    suspend fun deleteSchedules()

    suspend fun insertSchedule(schedule: Schedule)

    suspend fun insertSchedules(schedules: List<Schedule>)
}