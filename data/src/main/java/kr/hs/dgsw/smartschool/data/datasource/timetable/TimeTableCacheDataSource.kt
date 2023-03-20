package kr.hs.dgsw.smartschool.data.datasource.timetable

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

interface TimeTableCacheDataSource {

    suspend fun getTimeTables(): List<TimeTable>

    suspend fun insertTimeTable(timeTable: TimeTable)

    suspend fun insertTimeTables(timeTables: List<TimeTable>)

    suspend fun deleteTimeTables()
}