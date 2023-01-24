package kr.hs.dgsw.smartschool.domain.model.meal

import java.time.LocalDate

data class Meal(
    val date: LocalDate,
    val exists: Boolean,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
) {
    constructor(date: LocalDate) : this(
        date,
        false,
        "조식이 없는 날이에요.",
        "중식이 없는 날이에요.",
        "석식이 없는 날이에요."
    )
}
