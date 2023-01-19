package kr.hs.dgsw.smartschool.domain.exception

class BadRequestException(
    override val message: String?,
    private val fieldErrors: List<Pair<String, String>>,
) : RuntimeException()

/**
 * 요청한 token이 유효하지 않을 경우 발생하는 Exception.
 * Status가 401일 경우 발생!
 */
class UnAuthorizedException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청한 토큰에 접근 권한이 없을 경우 발생하는 Exception.
 * Status가 403일 경우 발생!
 */
class ForbiddenException(
    override val message: String?
) : RuntimeException()

/**
 * 요청한 Resource를 찾을 수 없을 때 발생하는 Exception
 * Status가 404일 경우 발생
 */
class NotFoundException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청이 지연될 경우 발생하는 Exception.
 * Status가 408일 경우 발생
 */
class TimeOutException(
    override val message: String?
) : RuntimeException()

/**
 * 요청이 현재 서버의 상태와 충돌될 때 발생하는 Exception.
 * Status가 409일 경우 발생
 */
class ConflictException(
    override val message: String?
) : RuntimeException()

/**
 * 요청 횟수가 초과한 경우 발생하는 error 입니다.
 * Status가 429일 경우 발생!
 */
class TooManyRequestsException(
    override val message: String?
) : RuntimeException()

/**
 * Server에서 발생하는 error 입니다.
 * Status가 50X일 경우 발생!
 */
class ServerException(
    override val message: String?
) : RuntimeException()

/**
 * 알 수 없는 에러입니다.
 */
class UnknownException(
    override val message: String?,
) : RuntimeException()

class OtherHttpException(
    val code: Int,
    override val message: String?,
) : RuntimeException()
