package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi

data class JoinState(
    val id: String = "",
    val pw: String = "",
    val checkedPw: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val tel: String = "",
    val position: String = "",

    val loading: Boolean = false,
)

sealed class JoinSideEffect {
    object SuccessJoin : JoinSideEffect()
    data class FailJoin(val throwable: Throwable): JoinSideEffect()
}
