package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

data class ApplyState(
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,
    val currentApplyType: Int = 0,

    val classrooms: List<Classroom> = emptyList(),
    val members: List<Member> = emptyList(),
    val students: List<Student> = emptyList(),
    val studyRooms: List<StudyRoom>? = null,

    val applyItemList: List<ApplyItem> = emptyList(),

    val loading: Boolean = false,
) {
    data class ApplyItem(
        val student: Student,
        val member: Member,
        val studyRoom: StudyRoom? = null,
        val classroom: Classroom,
    )
}

sealed class ApplySideEffect {
    data class ShowException(val exception: Throwable) : ApplySideEffect()
}
