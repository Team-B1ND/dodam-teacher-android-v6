package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.meal.MealEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface MealDao : BaseDao<MealEntity> {

    @Query("SELECT * FROM ${DodamTable.MEAL}")
    suspend fun getAllMeal(): List<MealEntity>

    @Query("SELECT * FROM ${DodamTable.MEAL} WHERE year=:year AND month=:month")
    suspend fun getMealByMonth(year: Int, month: Int): List<MealEntity>

    @Query("SELECT * FROM ${DodamTable.MEAL} WHERE year=:year AND month=:month AND day=:day")
    suspend fun getMeal(year: Int, month: Int, day: Int): MealEntity?

    @Query("DELETE FROM ${DodamTable.MEAL}")
    suspend fun deleteAllMeal()
}
