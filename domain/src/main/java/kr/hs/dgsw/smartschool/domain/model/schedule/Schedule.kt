package kr.hs.dgsw.smartschool.domain.model.schedule

import java.time.LocalDate

data class Schedule(
    val id: Int,
    val name: String,
    val place: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val target: String,
)
