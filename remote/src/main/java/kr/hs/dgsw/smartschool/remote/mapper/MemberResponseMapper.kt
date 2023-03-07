package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.remote.response.member.AllMemberResponse

internal fun AllMemberResponse.toMemberData(): MemberData =
    MemberData(
        students = students.toModel(),
        teachers = teachers.toModel()
    )