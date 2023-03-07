package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse

internal fun List<TeacherResponse>.toModel(): List<Teacher> =
    this.map {
        it.toModel()
    }

internal fun TeacherResponse.toModel(): Teacher =
    Teacher(
        id = id,
        member = member.toMember(),
        phone = phone,
        position = position,
        tel = tel,
    )