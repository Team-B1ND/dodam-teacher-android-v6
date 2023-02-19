package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi

data class JoinState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val exception: Throwable? = null,
)
