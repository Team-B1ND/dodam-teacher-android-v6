package kr.hs.dgsw.smartschool.domain.model.meal

import java.time.LocalDate

data class Calorie(
    val date: LocalDate,
    val breakfast: Double,
    val lunch: Double,
    val dinner: Double,
    val exists: Boolean,
) {
    constructor(date: LocalDate) : this(
        date = date,
        breakfast = 0.0,
        lunch = 0.0,
        dinner = 0.0,
        exists = false,
    )
}
