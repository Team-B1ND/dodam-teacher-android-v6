package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi

import kr.hs.dgsw.smartschool.domain.model.token.Token

data class LoginState(
    val loading: Boolean = false,
    val token: Token? = null,
    val exception: Throwable? = null,

    val id: String = "",
    val pw: String = "",
    val enableAutoLogin: Boolean = false,
)
