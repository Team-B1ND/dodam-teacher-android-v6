package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import java.time.LocalDate

interface MealRepository {

    suspend fun getMeal(date: LocalDate): Meal

    suspend fun getCalorieOfMeal(): Calorie
}
