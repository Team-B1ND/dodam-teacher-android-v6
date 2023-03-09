package kr.hs.dgsw.smartschool.domain.model.member.student

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class Student(
    val classroom: Classroom,
    val id: Int,
    val member: Member,
    val number: Int,
    val phone: String,
)
