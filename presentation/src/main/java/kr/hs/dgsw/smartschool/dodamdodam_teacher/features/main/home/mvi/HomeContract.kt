package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi

import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class HomeState(
    val isOutLoading: Boolean = false,
    val isMealLoading: Boolean = false,
    val banners: List<Banner> = emptyList(),

    val outgoingCount: Int = 0,
    val outsleepingCount: Int = 0,

    val meal: Meal? = null,

    val outErrorMessage: String = "",
    val mealErrorMessage: String = "",
)

sealed class HomeSideEffect {
    data class ToastError(val exception: Throwable) : HomeSideEffect()
}
