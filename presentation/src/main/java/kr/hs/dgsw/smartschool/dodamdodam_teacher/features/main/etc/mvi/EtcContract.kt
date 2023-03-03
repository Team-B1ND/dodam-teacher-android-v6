package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi

data class EtcState(
    val isLoading: Boolean = false,
)

sealed class EtcSideEffect {
    object SuccessLogout : EtcSideEffect()
    data class ToastLogoutErrorMessage(val throwable: Throwable) : EtcSideEffect()
}
