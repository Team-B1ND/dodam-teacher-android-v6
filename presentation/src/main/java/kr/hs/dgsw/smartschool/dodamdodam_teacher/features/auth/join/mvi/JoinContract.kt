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

    val checkTerms: Boolean = false,

    val loading: Boolean = false,

    val currentPage: Int = 0,
)

sealed class JoinSideEffect {
    object SuccessJoin : JoinSideEffect()
    data class FailJoin(val throwable: Throwable): JoinSideEffect()
}
