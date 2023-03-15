package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTable

data class StudyRoomState(
    val loading: Boolean = true,
    val exception: Throwable? = null,

    val studyRoomList : StudyRoomList = StudyRoomList(emptyList(),emptyList()),
    val placeList : List<Place> = emptyList(),
    val student : Student? = null,
    val timeTableList : List<TimeTable>? = null,

    val isWeekDay : Boolean? = null,

    val totalStudents : Int = 0,
    val firstClass : Int = 0,
    val secondClass : Int = 0,
    val thirdClass : Int = 0,
    val fourthClass : Int = 0,
)

sealed class StudyRoomSideEffect { data class Toast(val message: String) : StudyRoomSideEffect()
    data class ToastError(val exception: Throwable) : StudyRoomSideEffect()
}
