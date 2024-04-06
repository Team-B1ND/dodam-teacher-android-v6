package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

data class OutState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val currentOutType: Int = 0,

    val currentSelectedOutItem: OutItem? = null,

    val outGoings: List<OutItem> = emptyList(),
    val outSleepings: List<OutItem> = emptyList(),
    val classrooms: List<Classroom> = emptyList(),
    val members: List<Member> = emptyList(),
    val students: List<Member> = emptyList(),

    val getOutsLoading: Boolean = false,
    val showPrompt: Boolean = false,

    val refreshing: Boolean = false,
)

sealed class OutSideEffect {
    data class ShowException(val exception: Throwable) : OutSideEffect()
    data class SuccessControl(val message: String) : OutSideEffect()
}
