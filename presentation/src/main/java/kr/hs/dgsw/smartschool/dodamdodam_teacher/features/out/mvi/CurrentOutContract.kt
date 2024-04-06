package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class CurrentOutState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val currentOutType: Int = 0,

    val currentSelectedOutItem: Out? = null,

    val outGoings: List<Out> = emptyList(),
    val outSleepings: List<Out> = emptyList(),
    val classrooms: List<Classroom> = emptyList(),
    val members: List<Member> = emptyList(),
    val students: List<Member> = emptyList(),

    val getOutsLoading: Boolean = false,
    val showPrompt: Boolean = false,

    val refreshing: Boolean = false,
)

sealed class CurrentOutSideEffect {
    data class ShowException(val exception: Throwable) : CurrentOutSideEffect()
    data class SuccessControl(val message: String) : CurrentOutSideEffect()
}
