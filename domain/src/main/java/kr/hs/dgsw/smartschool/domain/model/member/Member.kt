package kr.hs.dgsw.smartschool.domain.model.member

import java.time.LocalDateTime

data class Member(
    val email: String,
    val id: String,
    val joinDate: String?,
    val name: String,
    val profileImage: String?,
    val role: MemberRole,
    val status: MemberStatus,
) {
    constructor(id: String,name : String, role: MemberRole) : this(
        email = "",
        id = id,
        joinDate = null,
        name = name,
        profileImage = null,
        role = role,
        status = MemberStatus.ACTIVE
    )
}
