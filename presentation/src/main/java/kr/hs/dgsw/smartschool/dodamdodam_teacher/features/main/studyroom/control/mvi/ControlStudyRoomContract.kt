package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi

import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

data class ControlStudyRoomState(
    val getMyInfoLoading: Boolean = false,
    val ctrlStudyRoomLoading: Boolean = false,

    val myInfo: Teacher? = null,
)

sealed class ControlStudyRoomSideEffect {
    data class ShowException(val exception: Throwable): ControlStudyRoomSideEffect()
}
