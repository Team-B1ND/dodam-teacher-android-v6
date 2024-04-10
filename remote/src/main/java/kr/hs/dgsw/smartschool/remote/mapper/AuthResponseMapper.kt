package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.data.auth.LoginData
import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDate
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.MemberStatus
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.remote.response.auth.LoginResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseStatus

internal fun LoginResponse.toLoginData(): LoginData =
    LoginData(
        member = member.toMember(),
        token = Token(token, refreshToken)
    )

internal fun MemberResponse.toMember(): Member =
    Member(
        email = email,
        id = id,
        name = name,
        profileImage = profileImage,
        role = role.toMemberRole(),
        status = status.toMemberStatus(),
        phone = phone,
        student = student?.toModel(),
        teacher = teacher?.toModel(),
        createdAt = createdAt?.yearDateTimeToLocalDate().toString(),
        modifiedAt = modifiedAt?.yearDateTimeToLocalDate().toString(),
    )

internal fun MemberResponseRole.toMemberRole(): MemberRole =
    when (this.name) {
        MemberRole.STUDENT.name -> MemberRole.STUDENT
        MemberRole.TEACHER.name -> MemberRole.TEACHER
        MemberRole.ADMIN.name -> MemberRole.ADMIN
        MemberRole.PARENT.name -> MemberRole.PARENT
        else -> MemberRole.STUDENT
    }

internal fun MemberResponseStatus.toMemberStatus(): MemberStatus =
    when (this.name) {
        MemberStatus.ACTIVE.name -> MemberStatus.ACTIVE
        MemberStatus.DEACTIVATED.name -> MemberStatus.DEACTIVATED
        else -> MemberStatus.DEACTIVATED
    }
