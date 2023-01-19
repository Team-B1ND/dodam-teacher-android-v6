package kr.hs.dgsw.smartschool.data.cache

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

interface MealCache {

    suspend fun getAllMeal(): List<Meal>

    suspend fun getMealByMonth(year: Int, month: Int): List<Meal>

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal?

    suspend fun deleteAllMeal()
}