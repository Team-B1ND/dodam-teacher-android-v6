package kr.hs.dgsw.smartschool.domain.model.studyroom

import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import java.time.LocalDate

data class StudyRoom(
    val id: Int,
    val date: LocalDate,
    val timeTable: TimeTable,
    val place: Place,
    val studentId: Int,
    val status: StudyRoomStatus,
    val teacher: Teacher?
)
