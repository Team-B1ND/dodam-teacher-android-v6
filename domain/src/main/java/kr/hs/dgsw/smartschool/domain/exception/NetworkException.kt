package kr.hs.dgsw.smartschool.domain.exception

private const val NETWORK_EXCEPTION_MESSAGE = "네트워크 연결을 확인해 주세요."

/**
 * 인터넷이 없을 경우 발생합니다.
 */

class NoInternetException : RuntimeException() {
    override val message: String
        get() = NETWORK_EXCEPTION_MESSAGE
}

class NoConnectivityException : RuntimeException() {
    override val message: String
        get() = NETWORK_EXCEPTION_MESSAGE
}
