package kr.hs.dgsw.smartschool.domain.repository

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule

interface ScheduleRepository {

    suspend fun getSchedules(startDate: LocalDate, endDate: LocalDate): List<Schedule>

    suspend fun getSchedule(id: Int): Schedule?
}