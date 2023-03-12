package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.mvi

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

data class ItmapDetailState(
    val loading: Boolean = false,
    val company: Company? = null,
)

sealed class ItmapDetailSideEffect {
    data class ShowException(val exception: Throwable): ItmapDetailSideEffect()
}