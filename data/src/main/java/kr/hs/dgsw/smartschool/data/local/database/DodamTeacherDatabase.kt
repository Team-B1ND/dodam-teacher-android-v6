package kr.hs.dgsw.smartschool.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.hs.dgsw.smartschool.data.local.dao.MealDao
import kr.hs.dgsw.smartschool.data.local.entity.meal.MealEntity

@Database(
    entities = [MealEntity::class], version = 1, exportSchema = false
)

abstract class DodamTeacherDatabase : RoomDatabase() {
   abstract fun mealDao(): MealDao
}