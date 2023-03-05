package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList

data class StudyRoomState(
    val loading: Boolean = true,
    val exception: Throwable? = null,
    val studyRoomList : StudyRoomList? = null,
)

sealed class StudyRoomEffect {
    data class Toast(val message: String) : StudyRoomEffect()
}
