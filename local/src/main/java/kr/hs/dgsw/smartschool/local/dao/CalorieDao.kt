package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.calorie.CalorieEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface CalorieDao : BaseDao<CalorieEntity> {

    @Query("SELECT * FROM ${DodamTable.CALORIE} WHERE year=:year AND month=:month")
    suspend fun getCalorieByMonth(year: Int, month: Int): List<CalorieEntity>

    @Query("SELECT * FROM ${DodamTable.CALORIE} WHERE year=:year AND month=:month AND day=:day")
    suspend fun getCalorie(year: Int, month: Int, day: Int): CalorieEntity?

    @Query("DELETE FROM ${DodamTable.CALORIE}")
    suspend fun deleteAllCalorie()
}