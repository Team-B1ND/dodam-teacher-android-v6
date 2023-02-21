package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi

data class LoginState(
    val isLoading: Boolean = false,

    val id: String = "",
    val pw: String = "",
    val enableAutoLogin: Boolean = false,
)

sealed class LoginSideEffect {
    object NavigateToHomeScreen : LoginSideEffect()
    data class ToastLoginErrorMessage(val errMsg: String) : LoginSideEffect()
}
