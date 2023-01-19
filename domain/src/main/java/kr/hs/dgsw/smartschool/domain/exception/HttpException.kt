package kr.hs.dgsw.smartschool.domain.exception

class BadRequestException(
    override val message: String?,
    private val fieldErrors: List<Pair<String, String>>,
): RuntimeException()

class OtherHttpException(
    val code: Int,
    override val message: String?,
) : RuntimeException()