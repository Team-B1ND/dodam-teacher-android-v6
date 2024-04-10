package kr.hs.dgsw.smartschool.domain.model.out

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class Out(
    val id: Int,
    val reason: String,
    val status: OutStatus,
    val student: Student,
    val rejectReason: String,
    val startOutDate: String,
    val endOutDate: String,
    val createdAt: String,
    val modifiedAt: String
)

data class Outgoing(
    val outgoings: List<OutItem>,
)

data class OutSleeping(
    val outsleepings: List<OutItem>,

)
