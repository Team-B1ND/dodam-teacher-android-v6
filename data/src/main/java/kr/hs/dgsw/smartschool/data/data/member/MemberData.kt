package kr.hs.dgsw.smartschool.data.data.member

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

data class MemberData(
    val students: List<Student>,
    val teachers: List<Teacher>,
)
