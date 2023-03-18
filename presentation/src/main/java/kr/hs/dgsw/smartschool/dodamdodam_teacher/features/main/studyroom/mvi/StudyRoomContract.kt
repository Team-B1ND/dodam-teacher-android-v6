package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

data class StudyRoomState(
    val loading: Boolean = true,
    val listLoading : Boolean = true,
    val exception: Throwable? = null,
    val refreshing : Boolean = false,

    val studyRoomList: List<StudyRoom> = emptyList(),
    val otherStudents: List<Student> = emptyList(),
    val placeList: List<Place> = emptyList(),
    val student: Student? = null,
    val timeTableList: List<TimeTable> = emptyList(),

    val classType : Int = 0,

    val isWeekDay: Boolean = true,

    val totalStudentsCount: Int = 0,
    val firstClassCount: Int = 0,
    val secondClassCount: Int = 0,
    val thirdClassCount: Int = 0,
    val fourthClassCount: Int = 0,
)

sealed class StudyRoomSideEffect {
    data class ToastError(val exception: Throwable) : StudyRoomSideEffect()
}
