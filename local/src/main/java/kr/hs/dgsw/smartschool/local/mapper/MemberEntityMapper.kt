package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeHourToLocalDate
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.MemberStatus
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity

internal fun List<MemberEntity>.toModel(): List<Member> =
    this.map {
        it.toModel()
    }

internal fun MemberEntity.toModel(): Member =
    Member(
        email = email,
        id = id,
        joinDate = joinDate?.yearDateTimeHourToLocalDate(),
        name = name,
        profileImage = profileImage,
        role = role.toMemberRole(),
        status = status.toMemberStatus(),
    )

internal fun String.toMemberRole(): MemberRole = when (this) {
    MemberRole.TEACHER.name -> MemberRole.TEACHER
    MemberRole.STUDENT.name -> MemberRole.STUDENT
    MemberRole.ADMIN.name -> MemberRole.ADMIN
    MemberRole.PARENT.name -> MemberRole.PARENT
    else -> MemberRole.STUDENT
}

internal fun String.toMemberStatus(): MemberStatus = when (this) {
    MemberStatus.ACTIVE.name -> MemberStatus.ACTIVE
    MemberStatus.DEACTIVATED.name -> MemberStatus.DEACTIVATED
    else -> MemberStatus.DEACTIVATED
}

internal fun List<Member>.toEntity(): List<MemberEntity> =
    this.map {
        it.toEntity()
    }

internal fun Member.toEntity(): MemberEntity {
    return MemberEntity(
        id = id,
        email = email,
        joinDate = joinDate?.toString(),
        name = name,
        profileImage = profileImage,
        role = role.name,
        status = status.name
    )
}
