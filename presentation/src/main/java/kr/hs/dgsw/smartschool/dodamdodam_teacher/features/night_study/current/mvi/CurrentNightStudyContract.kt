package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudy
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class CurrentNightStudyState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,

    val currentSelectedNightStudy: NightStudy? = null,

    val isLoading: Boolean = false,
    val showPrompt: Boolean = false,

    val nightStudies: List<NightStudy> = emptyList(),
    val classrooms: List<Classroom> = emptyList(),
    val members: List<Member> = emptyList(),
    val students: List<Student> = emptyList(),

    val refreshing: Boolean = false,
)

sealed class CurrentNightStudySideEffect {
    data class SuccessControl(val message: String) : CurrentNightStudySideEffect()
    data class ShowException(val exception: Throwable) : CurrentNightStudySideEffect()
}
