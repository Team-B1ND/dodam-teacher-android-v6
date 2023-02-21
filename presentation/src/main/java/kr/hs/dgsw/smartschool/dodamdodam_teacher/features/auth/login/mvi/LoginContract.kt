package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi

import kr.hs.dgsw.smartschool.domain.model.token.Token

data class LoginState(
    val id: String = "",
    val pw: String = "",
    val enableAutoLogin: Boolean = false,
)

sealed class LoginSideEffect {

    data class SuccessLogin(
        val token: Token
    ): LoginSideEffect()

    data class ErrorLogin(
        val exception: Throwable
    ): LoginSideEffect()
}
