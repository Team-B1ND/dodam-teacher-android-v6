package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.MealService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class MealRemoteDataSourceImpl @Inject constructor(
    private val mealService: MealService
) : MealRemoteDataSource {

    override suspend fun getMeal(
        day: Int,
        month: Int,
        year: Int,
    ): Meal = dodamApiCall {
        mealService.getMeal(
            day,
            month,
            year
        ).data.toModel()
    }

    override suspend fun getMealOfMonth(
        month: Int,
        year: Int,
    ): MealList = dodamApiCall {
        mealService.getMealOfMonth(
            month,
            year
        ).data.toModel()
    }

    override suspend fun getCalorieOfMeal(month: Int, year: Int): List<Calorie> = dodamApiCall {
        mealService.getCalorieOfMeal(month = month, year = year).data.toModel()
    }
}
