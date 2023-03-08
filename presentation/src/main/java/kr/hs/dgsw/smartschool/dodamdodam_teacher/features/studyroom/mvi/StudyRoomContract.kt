package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList

data class StudyRoomState(
    val loading: Boolean = true,
    val exception: Throwable? = null,
    val studyRoomList : StudyRoomList? = null,
    val isWeekDay : Boolean? = null,
    val firstClass : Int? = null,
    val secondClass : Int? = null,
    val thirdClass : Int? = null,
    val fourthClass : Int? = null,
)

sealed class StudyRoomSideEffect {
    data class Toast(val message: String) : StudyRoomSideEffect()
    data class ToastError(val throwable: Throwable) : StudyRoomSideEffect()
}
