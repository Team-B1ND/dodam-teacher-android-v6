package kr.hs.dgsw.smartschool.data.datasource.meal

import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList

interface MealCacheDataSource {

    suspend fun getAllMeal(): MealList

    suspend fun getMealByMonth(year: Int, month: Int): MealList

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal?

    suspend fun insertMealList(mealList: MealList)

    suspend fun deleteAllMeal()
}
