package kr.hs.dgsw.smartschool.domain.model.evening_study

import java.time.LocalDateTime

data class NightStudy(
    val id: Int,
    val content: String,
    val allowCheck: AllowCheck,
    val reason: String?,
    val student: NightStudyStudent,
    val place: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val phone: Boolean,
)
