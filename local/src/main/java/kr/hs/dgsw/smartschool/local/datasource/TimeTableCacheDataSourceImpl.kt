package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.local.dao.TimeTableDao
import kr.hs.dgsw.smartschool.local.mapper.toTimeTableEntity
import kr.hs.dgsw.smartschool.local.mapper.toTimeTableEntityList
import kr.hs.dgsw.smartschool.local.mapper.toTimeTableList
import javax.inject.Inject

class TimeTableCacheDataSourceImpl @Inject constructor(
    private val timeTableDao: TimeTableDao
) : TimeTableCacheDataSource {

    override suspend fun getTimeTables(): List<TimeTable> =
        timeTableDao.getTimeTables().toTimeTableList()

    override suspend fun insertTimeTable(timeTable: TimeTable) =
        timeTableDao.insert(timeTable.toTimeTableEntity())

    override suspend fun insertTimeTables(timeTables: List<TimeTable>) =
        timeTableDao.insert(timeTables.toTimeTableEntityList())

    override suspend fun deleteTimeTables() =
        timeTableDao.deleteTimeTables()
}
