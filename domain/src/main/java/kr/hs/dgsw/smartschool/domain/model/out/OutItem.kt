package kr.hs.dgsw.smartschool.domain.model.out

import java.time.LocalDateTime
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

data class OutItem(
    val id: Int,
    val reason: String,
    val status: OutStatus,
    val studentId: Int,
    val teacher: Teacher?,
    val startOutDate: LocalDateTime,
    val endOutDate: LocalDateTime,
    val arrivedDate: LocalDateTime?,
    val checkedDate: LocalDateTime?,
)
