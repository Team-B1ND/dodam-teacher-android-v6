package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class PointState(
    val page: Int = 1,
    val currentGrade: Int = 0,
    val students: List<Student> = emptyList()
)

sealed class PointSideEffect {

}