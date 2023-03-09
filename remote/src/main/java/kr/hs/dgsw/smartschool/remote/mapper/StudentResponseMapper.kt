package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

internal fun List<StudentResponse>.toModel(): List<Student> =
    this.map {
        it.toModel()
    }

internal fun StudentResponse.toModel(): Student =
    Student(
        classroom = classroom.toModel(),
        id = id,
        member = member.toMember(),
        number = number,
        phone = phone,
    )
