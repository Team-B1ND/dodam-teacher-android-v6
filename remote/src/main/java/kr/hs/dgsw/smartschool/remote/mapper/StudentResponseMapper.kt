package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

internal fun List<StudentResponse>.toModel(): List<Student> =
    this.map {
        it.toModel()
    }

internal fun StudentResponse.toModel(): Student =
    Student(
        id = id,
        name = name,
        grade = grade,
        room = room,
        number = number,

    )
