package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.data.utils.toLocalDate
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.local.entity.calorie.CalorieEntity

internal fun List<CalorieEntity>.toModel(): List<Calorie> =
    this.map {
        it.toModel()
    }

internal fun CalorieEntity.toModel(): Calorie =
    Calorie(
        date = toLocalDate(year, month, day),
        breakfast = breakfast,
        lunch = lunch,
        dinner = dinner,
        exists = exists,
    )

internal fun List<Calorie>.toEntity(): List<CalorieEntity> =
    this.map {
        it.toEntity()
    }

internal fun Calorie.toEntity(): CalorieEntity =
    CalorieEntity(
        year = date.year,
        month = date.monthValue,
        day = date.dayOfMonth,
        exists = exists,
        breakfast = breakfast,
        lunch = lunch,
        dinner = dinner,
    )