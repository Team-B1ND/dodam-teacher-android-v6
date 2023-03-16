package kr.hs.dgsw.smartschool.data.datasource.timetable

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

interface TimeTableRemoteDataSource {
    suspend fun getTimeTables(): List<TimeTable>
}
