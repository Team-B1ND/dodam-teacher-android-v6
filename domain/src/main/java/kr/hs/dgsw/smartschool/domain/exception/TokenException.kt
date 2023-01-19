package kr.hs.dgsw.smartschool.domain.exception

private const val EXPIRED_REFRESH_TOKEN_MESSAGE = "토큰이 만료되어 로그인이 필요해요."
private const val NOT_FOUND_REFRESH_TOKEN_MESSAGE = "토큰이 없어 로그인이 필요해요."

/**
 * refresh 토큰이 만료된 경우 발생하는 Exception
 */
class ExpiredRefreshTokenException : RuntimeException() {
    override val message: String
        get() = EXPIRED_REFRESH_TOKEN_MESSAGE
}

/**
 * refresh 토큰이 없을 경우 발생하는 Exception
 */
class NotFoundRefreshTokenException : RuntimeException() {
    override val message: String
        get() = NOT_FOUND_REFRESH_TOKEN_MESSAGE
}