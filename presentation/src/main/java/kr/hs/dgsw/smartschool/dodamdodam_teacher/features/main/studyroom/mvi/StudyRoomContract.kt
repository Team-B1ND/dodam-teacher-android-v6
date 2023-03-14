package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTable

data class StudyRoomState(
    val loading: Boolean = true,
    val exception: Throwable? = null,
    val studyRoomList : StudyRoomList? = null,

    val placeList : List<Place>? = null,
    val timeTableId : Int? = null,

    val isWeekDay : Boolean? = null,

    val totalStudents : Int? = null,
    val firstClass : Int? = null,
    val secondClass : Int? = null,
    val thirdClass : Int? = null,
    val fourthClass : Int? = null,
)

sealed class StudyRoomSideEffect { data class Toast(val message: String) : StudyRoomSideEffect()
    data class ToastError(val exception: Throwable) : StudyRoomSideEffect()
}
