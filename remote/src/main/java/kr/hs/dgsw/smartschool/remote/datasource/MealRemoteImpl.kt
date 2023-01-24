package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.remote.MealRemote
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.remote.mapper.toCalorieModel
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.MealService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class MealRemoteImpl @Inject constructor(
    private val mealService: MealService
) : MealRemote {

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

    override suspend fun getCalorieOfMeal(): Calorie = dodamApiCall {
        mealService.getCalorieOfMeal().data.toCalorieModel()
    }
}
