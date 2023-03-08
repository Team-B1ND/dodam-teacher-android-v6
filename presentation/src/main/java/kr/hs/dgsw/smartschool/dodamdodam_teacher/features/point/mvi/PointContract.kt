package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member

data class PointState(
    val classrooms: List<Classroom> = emptyList(),

    val page: Int = 1,
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val students: List<PointStudent> = emptyList(),
) {
    data class PointStudent(
        val member: Member,
        val isChecked: Boolean = false,
    )
}

sealed class PointSideEffect {
    data class ShowException(val exception: Throwable): PointSideEffect()
}