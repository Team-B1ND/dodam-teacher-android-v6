package kr.hs.dgsw.smartschool.domain.repository

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

interface MealRepository {

    suspend fun getMeal(date: LocalDate): Meal

    suspend fun getCalorieOfMeal(): Calorie
}