package kr.hs.dgsw.smartschool.domain.model.out

import java.time.LocalDateTime

data class OutItem(
    val id: Int,
    val reason: String,
    val status: OutStatus,
    val studentId: Int,
    val teacherId: Int?,
    val startOutDate: LocalDateTime,
    val endOutDate: LocalDateTime,
    val arrivedDate: LocalDateTime?,
    val checkedDate: LocalDateTime?,
)
