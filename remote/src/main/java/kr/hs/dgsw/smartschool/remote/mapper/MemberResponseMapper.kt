package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.model.place.Place
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
        position = this.teacher?.position ?: "",
        tel = this.teacher?.position ?: "",
    )

internal fun MemberResponse.toModelStudent(): Student =
    Student(
        classroom = Classroom(
            id = 0,
            grade = this.student?.grade ?: 0,
            place = Place(0),
            room = this.student?.room ?: 0
        ),
        id = this.student?.id ?: 0,
        member = this.toMember(),
        phone = phone,
        number = this.student?.number ?: 0
    )
