package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

data class ItmapState(
    val companies: List<Company> = emptyList(),
    val isRotated: Boolean = false,
    val isRefreshing: Boolean = false,
)

sealed class ItmapSideEffect {
    data class ShowException(val exception: Throwable): ItmapSideEffect()
}
