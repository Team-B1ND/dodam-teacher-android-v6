package kr.hs.dgsw.smartschool.domain.model.member.teacher

import kr.hs.dgsw.smartschool.domain.model.member.Member

data class Teacher(
    val id: Int,
    val member: Member,
    val phone: String,
    val position: String,
    val tel: String,
)
