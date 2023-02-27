package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity

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
