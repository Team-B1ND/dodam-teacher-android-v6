package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi

data class LoginState(
    val isLoading: Boolean = false,
    val exception: Throwable? = null,

    val id: String = "",
    val pw: String = "",
    val enableAutoLogin: Boolean = false,
)

sealed class LoginSideEffect {
    object NavigateToHomeScreen : LoginSideEffect()
}
