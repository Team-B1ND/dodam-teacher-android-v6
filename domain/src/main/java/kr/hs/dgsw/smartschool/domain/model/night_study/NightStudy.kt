package kr.hs.dgsw.smartschool.domain.model.night_study

import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class NightStudy(
    val id: Int,
    val content: String,
    val state: OutStatus,
    val reason: String?,
    val student: NightStudyStudent,
    val place: String,
    val startAt: LocalDate,
    val endAt: LocalDate,
    val createdAt: String,
    val modifiedAt: String,
    val isPhone: Boolean,
)
