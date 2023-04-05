package kr.hs.dgsw.smartschool.data.data.auth

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.token.Token

data class LoginData(
    val member: Member,
    val token: Token,
)
