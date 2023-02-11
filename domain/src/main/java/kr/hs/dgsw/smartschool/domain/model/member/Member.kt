package kr.hs.dgsw.smartschool.domain.model.member

import java.time.LocalDate

data class Member(
    val email: String,
    val id: String,
    val joinDate: LocalDate?,
    val name: String,
    val profileImage: String?,
    val role: MemberRole,
    val status: MemberStatus,
)
