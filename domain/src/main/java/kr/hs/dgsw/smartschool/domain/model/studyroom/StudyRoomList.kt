package kr.hs.dgsw.smartschool.domain.model.studyroom

import kr.hs.dgsw.smartschool.domain.model.member.student.Student

data class StudyRoomList(
    val studyRoomList: List<StudyRoom>,
    val otherStudents: List<Student>
)
