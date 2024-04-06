package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

data class EtcState(
    val isLoading: Boolean = false,
    val myInfo: Member? = null,
    val showPrompt: Boolean = false,
)

sealed class EtcSideEffect {
    object SuccessLogout : EtcSideEffect()
    data class ShowException(val throwable: Throwable) : EtcSideEffect()
}
