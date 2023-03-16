package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

interface TimeTableRepository {

    suspend fun getTimeTables(): List<TimeTable>
}
