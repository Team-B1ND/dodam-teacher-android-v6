package kr.hs.dgsw.smartschool.data.datasource.calorie

import kr.hs.dgsw.smartschool.domain.model.meal.Calorie

interface CalorieCacheDataSource {

    suspend fun getCalorieByMonth(year: Int, month: Int): List<Calorie>

    suspend fun getCalorie(year: Int, month: Int, day: Int): Calorie?

    suspend fun insertCalories(calories: List<Calorie>)

    suspend fun insertCalorie(calorie: Calorie)

    suspend fun deleteAllCalorie()
}
