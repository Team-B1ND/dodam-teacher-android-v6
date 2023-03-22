package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.timetable.TimeTableEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface TimeTableDao : BaseDao<TimeTableEntity> {

    @Query("SELECT * FROM ${DodamTable.TIMETABLE}")
    suspend fun getTimeTables(): List<TimeTableEntity>

    @Query("DELETE FROM ${DodamTable.TIMETABLE}")
    suspend fun deleteTimeTables()
}
