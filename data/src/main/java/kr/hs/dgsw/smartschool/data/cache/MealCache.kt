package kr.hs.dgsw.smartschool.data.cache

import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList

interface MealCache {

    suspend fun getAllMeal(): MealList

    suspend fun getMealByMonth(year: Int, month: Int): MealList

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal?

    suspend fun insertMealList(mealList: MealList)

    suspend fun deleteAllMeal()
}
