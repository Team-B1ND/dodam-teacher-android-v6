package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.remote.response.meal.MealResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun MealResponse.toModel(): Meal {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return Meal(
        date = LocalDate.parse(date, formatter),
        exists = exists,
        breakfast = breakfast ?: "조식이 없는 날이에요.",
        lunch = lunch ?: "중식이 없는 날이에요.",
        dinner = dinner ?: "석식이 없는 날이에요."
    )
}

internal fun List<MealResponse>.toModel(): MealList =
    MealList(
        mealList = this.map { it.toModel() }
    )

internal fun String?.toCalorieModel(): Calorie =
    Calorie(
        calorie = this?.lowercase()?.replace("kcal", "")?.toDouble() ?: 0.0
    )
