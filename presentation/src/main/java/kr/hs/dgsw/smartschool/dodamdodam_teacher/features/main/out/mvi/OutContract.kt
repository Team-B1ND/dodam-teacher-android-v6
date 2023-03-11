package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi

import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

data class OutState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val currentOutType: Int = 0,

    val classrooms: List<Classroom> = emptyList(),
    val showPrompt: Boolean = false,
)

sealed class OutSideEffect {
    data class ShowException(val exception: Throwable) : OutSideEffect()
}
