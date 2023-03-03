package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi

import java.time.LocalDateTime

data class HomeState(
    val outUpdateDate: LocalDateTime? = null,
    val outgoingCount: Int = 0,
    val outsleepingCount: Int = 0,
)

sealed class HomeSideEffect
