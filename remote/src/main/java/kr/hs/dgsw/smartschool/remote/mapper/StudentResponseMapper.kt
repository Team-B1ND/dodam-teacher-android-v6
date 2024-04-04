package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

internal fun List<StudentResponse>.toModel(): List<Student> =
    this.map {
        it.toModel()
    }

internal fun StudentResponse.toModel(): Student =
    Student(
        classroom = Classroom(0),
        id = id,
        member = Member(),
        number = number,
        phone = "",
    )
