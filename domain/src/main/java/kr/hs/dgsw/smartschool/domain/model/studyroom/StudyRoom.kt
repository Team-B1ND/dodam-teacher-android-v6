package kr.hs.dgsw.smartschool.domain.model.studyroom

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable

data class StudyRoom(
    val id: Int,
    val date: String,
    val timeTable: TimeTable,
    val place: Place,
    val student: Student,
    val status: StudyRoomStatus,
    val teacher: Teacher?
)

// TODO StudentId, TimeTableId, PlaceId
