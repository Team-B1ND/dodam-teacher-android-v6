package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.point.PointReason

data class PointState(
    val classrooms: List<Classroom> = emptyList(),

    val page: Int = 1,
    val currentGrade: Int = 0,
    val currentClassroom: Int = 0,

    val members: List<Member> = emptyList(),
    val students: List<Student> = emptyList(),
    val pointStudents: List<PointStudent> = emptyList(),

    val currentPlace: Int = 0,
    val currentPointType: Int = 0,
    val minusReason: List<PointReason> = emptyList(),
    val bonusReason: List<PointReason> = emptyList(),

    val currentSelectedReason: PointReason? = null,
    val givePointLoading: Boolean = false,
) {
    data class PointStudent(
        val id: String,
        val studentId: Int,
        val name: String,
        val grade: Int,
        val room: Int,
        val profileImage: String,

        val isChecked: Boolean = false,
    )
}

sealed class PointSideEffect {
    object SuccessGivePoint : PointSideEffect()
    data class ShowException(val exception: Throwable) : PointSideEffect()
}
