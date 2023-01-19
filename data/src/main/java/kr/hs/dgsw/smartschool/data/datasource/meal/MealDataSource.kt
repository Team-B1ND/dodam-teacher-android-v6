package kr.hs.dgsw.smartschool.data.datasource.meal

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

interface MealDataSource {

    suspend fun getMeal(date: LocalDate): Meal

    suspend fun getCalorie(): Calorie

}