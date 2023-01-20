package kr.hs.dgsw.smartschool.data.repository

import java.time.LocalDate
import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.meal.MealDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource,
) : MealRepository {

    override suspend fun getMeal(date: LocalDate): Meal =
        mealDataSource.getMeal(date)

    override suspend fun getCalorieOfMeal(): Calorie =
        mealDataSource.getCalorie()
}