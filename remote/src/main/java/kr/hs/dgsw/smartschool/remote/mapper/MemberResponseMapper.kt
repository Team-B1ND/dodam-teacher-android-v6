package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.remote.response.member.AllMemberResponse

internal fun AllMemberResponse.toMemberData(): MemberData =
    MemberData(
        students = students.toModel(),
        teachers = teachers.toModel()
    )

// internal fun MemberResponse.toModel(): Teacher =
//    Teacher(
//        id = 0,
//        member = this.toMember(),
//        phone = phone,
//        position = this.teacher?.position?: "",
//        tel = this.teacher?.position?: "",
//    )

// internal fun MemberResponse.toModelStudent(): Student =
//    Student(
//        id = this.student?.id?: 0,
//        member = this.toMember(),
//        phone = phone,
//        number = this.student?.number?: 0
//    )
