package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.remote.response.member.AllMemberResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse

internal fun AllMemberResponse.toMemberData(): MemberData =
    MemberData(
        students = students.toModel(),
        teachers = teachers.toModel()
    )

internal fun MemberResponse.toModel(): Teacher =
    Teacher(
        id = 0,
        member = this.toMember(),
        phone = phone,
        position = "",
        tel = "010-0000-0000",
    )

internal fun MemberResponse.toModelStudent(): Student =
    Student(
        classroom = Classroom(0),
        id = 0,
        member = this.toMember(),
        phone = phone,
        number = 0
    )