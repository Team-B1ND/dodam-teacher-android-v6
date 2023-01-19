package kr.hs.dgsw.smartschool.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.data.local.entity.meal.MealEntity
import kr.hs.dgsw.smartschool.data.local.table.DodamTable

@Dao
interface MealDao {

    @Query("SELECT * FROM ${DodamTable.MEAL}")
    suspend fun getAllMeal(): List<MealEntity>

}
