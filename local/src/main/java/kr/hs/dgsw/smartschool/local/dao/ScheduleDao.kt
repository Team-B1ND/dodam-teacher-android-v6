package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.schedule.ScheduleEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {

    @Query("SELECT * FROM ${DodamTable.SCHEDULE} where startMonth=:startMonth and startYear=:startYear")
    suspend fun getSchedules(startYear: Int, startMonth: Int): List<ScheduleEntity>

    @Query("SELECT * FROM ${DodamTable.SCHEDULE} where id=:id")
    suspend fun getSchedule(id: Int): ScheduleEntity?

    @Query("DELETE FROM ${DodamTable.SCHEDULE}")
    suspend fun deleteSchedules()
}
