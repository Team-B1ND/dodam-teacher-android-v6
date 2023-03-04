package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.local.entity.meal.MealEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun MealEntity.toModel(): Meal {
    val date = "$year-%02d-%02d".format(month, day)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return Meal(
        date = LocalDate.parse(date, formatter),
        exists = exists,
        breakfast = breakfast,
        lunch = lunch,
        dinner = dinner
    )
}

internal fun List<MealEntity>.toModel(): MealList =
    MealList(mealList = this.map { it.toModel() })

internal fun Meal.toEntity(): MealEntity {
    return MealEntity(
        year = date.year,
        month = date.monthValue,
        day = date.dayOfMonth,
        exists = exists,
        breakfast = breakfast,
        lunch = lunch,
        dinner = dinner
    )
}

internal fun MealList.toEntity(): List<MealEntity> =
    this.mealList.map { it.toEntity() }
