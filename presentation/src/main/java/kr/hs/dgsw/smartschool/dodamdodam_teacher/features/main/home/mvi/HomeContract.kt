package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi

import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import java.time.LocalDateTime

data class HomeState(
    val isOutLoading: Boolean = false,
    val isMealLoading: Boolean = false,
    val isStudyLoading: Boolean = false,
    val banners: List<Banner> = emptyList(),
    val refreshTime: LocalDateTime? = null,

    val outgoingCount: Int = 0,
    val outsleepingCount: Int = 0,

    val firstClassCount: Int = 0,
    val secondClassCount: Int = 0,
    val thirdClassCount: Int = 0,
    val fourthClassCount: Int = 0,
    val allStudentsCount: Int = 0,
    val isWeekDay: Boolean = true,

    val meal: Meal? = null,

    val outErrorMessage: String = "",
    val mealErrorMessage: String = "",

    val refreshing: Boolean = false,
)

sealed class HomeSideEffect {
    data class ToastError(val exception: Throwable) : HomeSideEffect()
}
