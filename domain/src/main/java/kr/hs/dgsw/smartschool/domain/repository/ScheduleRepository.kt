package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import java.time.LocalDate

interface ScheduleRepository {

    suspend fun getSchedules(startDate: LocalDate, endDate: LocalDate): List<Schedule>

    suspend fun getSchedule(id: Int): Schedule?
}
