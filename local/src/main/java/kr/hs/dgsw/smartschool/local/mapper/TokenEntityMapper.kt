package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.local.entity.token.TokenEntity

internal fun Token.toEntity(): TokenEntity {
    return TokenEntity(
        token = token,
        refreshToken = refreshToken,
    )
}

internal fun TokenEntity.toModel(): Token {
    return Token(
        token = token,
        refreshToken = refreshToken,
    )
}
