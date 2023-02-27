package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.mvi

data class MainState(
    val enableAutoLogin: Boolean? = null
)

sealed class MainSideEffect {
    data class ToastGetEnableAutoLoginErrorMessage(val throwable: Throwable) : MainSideEffect()
}
