package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

data class ApplyState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val currentApplyType: Int = 0,

    val classrooms: List<Classroom> = emptyList(),
)

sealed class ApplySideEffect {
    data class ShowException(val exception: Throwable) : ApplySideEffect()
}
