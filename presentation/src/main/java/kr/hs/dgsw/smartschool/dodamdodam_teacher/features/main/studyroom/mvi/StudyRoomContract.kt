package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

data class StudyRoomState(
    val students: List<Student> = emptyList(),
    val studyRooms: List<StudyRoom> = emptyList(),
    val timeTables: List<TimeTable> = emptyList()
)

sealed class StudyRoomSideEffect {
    data class ShowException(val exception: Throwable) : StudyRoomSideEffect()
}
