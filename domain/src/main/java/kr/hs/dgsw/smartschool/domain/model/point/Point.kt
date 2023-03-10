package kr.hs.dgsw.smartschool.domain.model.point

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import java.time.LocalDate

data class Point(
    val id: Int,
    val type: PointType,
    val place: PointPlace,
    val reason: String,
    val score: Int,
    val student: Student,
    val teacher: Teacher,
    val givenDate: LocalDate,
)
