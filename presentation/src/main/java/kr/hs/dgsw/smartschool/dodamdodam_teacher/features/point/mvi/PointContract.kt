package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class PointState(
    val classrooms: List<Classroom> = emptyList(),

    val page: Int = 1,
    val currentGrade: Int = 0,
    val students: List<Student> = emptyList()
)

sealed class PointSideEffect {
    data class FailToGetClassrooms(val exception: Throwable): PointSideEffect()
}