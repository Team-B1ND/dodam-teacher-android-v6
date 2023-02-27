package kr.hs.dgsw.smartschool.data.datasource.meal

import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList

interface MealRemoteDataSource {

    suspend fun getMeal(day: Int, month: Int, year: Int): Meal

    suspend fun getMealOfMonth(month: Int, year: Int): MealList

    suspend fun getCalorieOfMeal(): Calorie
}
