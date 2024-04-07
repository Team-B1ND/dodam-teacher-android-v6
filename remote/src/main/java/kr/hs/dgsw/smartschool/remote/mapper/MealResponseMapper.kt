package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.remote.response.meal.CalorieResponse
import kr.hs.dgsw.smartschool.remote.response.meal.MealResponse
import kotlin.math.roundToInt

internal fun MealResponse.toModel(): Meal =
    Meal(
        date = date.yearDateToLocalDate(),
        exists = exists,
        breakfast = breakfast?.details?.mapIndexed { index, menuDetailResponse ->
            if (index == breakfast.details.lastIndex) {
                menuDetailResponse.name
            } else {
                "${menuDetailResponse.name} , "
            }
        }?.joinToString("") ?: "조식이 없는 날이에요.",
        lunch = lunch?.details?.mapIndexed { index, menuDetailResponse ->
            if (index == lunch.details.lastIndex) {
                menuDetailResponse.name
            } else {
                "${menuDetailResponse.name} , "
            }
        }?.joinToString("") ?: "중식이 없는 날이에요.",
        dinner = dinner?.details?.mapIndexed { index, menuDetailResponse ->
            if (index == dinner.details.lastIndex) {
                menuDetailResponse.name
            } else {
                "${menuDetailResponse.name} , "
            }
        }?.joinToString("") ?: "석식이 없는 날이에요.",
        calorie = ((breakfast?.calorie ?: 0.0) + (lunch?.calorie ?: 0.0) + (dinner?.calorie ?: 0.0)).roundToInt().toDouble()
    )

internal fun List<MealResponse>.toModel(): MealList =
    MealList(
        mealList = this.map { it.toModel() }
    )

internal fun List<CalorieResponse>.toModel(): List<Calorie> =
    this.map {
        it.toModel()
    }

internal fun CalorieResponse.toModel(): Calorie =
    Calorie(
        date = date.yearDateToLocalDate(),
        breakfast = getMealCalorie(breakfast ?: "0.0"),
        lunch = getMealCalorie(lunch ?: "0.0"),
        dinner = getMealCalorie(dinner ?: "0.0"),
        exists = exists
    )

private fun getMealCalorie(meal: String): Double =
    meal.lowercase().replace(" kcal", "").toDouble()
