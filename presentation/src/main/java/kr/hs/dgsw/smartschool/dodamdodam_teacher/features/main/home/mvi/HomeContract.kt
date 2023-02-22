package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi

data class HomeState(
   val isLoading: Boolean = false,
)

sealed class HomeSideEffect {
    object SuccessLogout : HomeSideEffect()
    data class ToastLogoutErrorMessage(val throwable: Throwable) : HomeSideEffect()
}
