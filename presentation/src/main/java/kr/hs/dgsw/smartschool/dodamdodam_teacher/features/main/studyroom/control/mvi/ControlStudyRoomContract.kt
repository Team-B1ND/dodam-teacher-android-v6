package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi

import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

data class ControlStudyRoomState(
    val getMyInfoLoading: Boolean = false,
    val ctrlStudyRoomLoading: Boolean = false,


    val myInfo: Teacher? = null,
    val places: List<Place> = emptyList(),
    val timeTables: List<TimeTable> = emptyList(),

    val myStudyRooms: List<StudyRoom> = emptyList(),

    val selectedStudyRoom: Map<Int, StudyRoomItem> = emptyMap(),
)

sealed class ControlStudyRoomSideEffect {
    data class ShowException(val exception: Throwable) : ControlStudyRoomSideEffect()
    object SuccessCtrl : ControlStudyRoomSideEffect()
}
