package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.model.meal.Meal

data class MealState(
    val currentDate: LocalDate = LocalDate.now(),
    val showDialog: Boolean = false,
    val calorie: String = "",

    val getMealLoading: Boolean = false,
    val getCalorieLoading: Boolean = false,
    val meal: Meal? = null,
)

sealed class MealSideEffect {
    data class ToastError(val exception: Throwable?) : MealSideEffect()
}
