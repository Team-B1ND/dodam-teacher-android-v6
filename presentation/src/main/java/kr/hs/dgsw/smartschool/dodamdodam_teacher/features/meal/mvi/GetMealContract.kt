package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi

import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class GetMealState(
    val loading: Boolean = true,
    val exception: Throwable? = null,
    val meal: Meal? = null,
)

sealed class GetMealSideEffect {
    data class Toast(val message: String) : GetMealSideEffect()
}
