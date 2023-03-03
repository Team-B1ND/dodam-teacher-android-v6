package kr.hs.dgsw.smartschool.domain.exception

/**
 * Member의 역할이 Teacher가 아닐 경우 발생
 */
class UncertifiedRoleException(
    override val message: String?,
) : RuntimeException()
