package kr.hs.dgsw.smartschool.domain.model.meal

import java.time.LocalDate

data class Meal(
    val date: LocalDate,
    val exists: Boolean,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
)