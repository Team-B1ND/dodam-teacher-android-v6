package kr.hs.dgsw.smartschool.local.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.local.entity.meal.MealEntity

internal fun MealEntity.toModel(): Meal {
    val date = "${year}-${month}-${day}"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return Meal(
        date = LocalDate.parse(date, formatter),
        exists = exists,
        breakfast = breakfast ?: "조식이 없는 날이에요.",
        lunch = lunch ?: "중식이 없는 날이에요.",
        dinner = dinner ?: "석식이 없는 날이에요."
    )
}

internal fun List<MealEntity>.toModel(): List<Meal> =
    this.map { it.toModel() }