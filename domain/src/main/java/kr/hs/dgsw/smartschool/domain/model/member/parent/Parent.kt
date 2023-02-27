package kr.hs.dgsw.smartschool.domain.model.member.parent

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class Parent(
    val id: Int,
    val phone: String,
    val student: Student,
)
