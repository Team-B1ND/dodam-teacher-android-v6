package kr.hs.dgsw.smartschool.domain.model.member

import java.time.LocalDateTime

data class Member(
    val email: String,
    val id: String,
    val joinDate: LocalDateTime?,
    val name: String,
    val profileImage: String?,
    val role: MemberRole,
    val status: MemberStatus,
)
