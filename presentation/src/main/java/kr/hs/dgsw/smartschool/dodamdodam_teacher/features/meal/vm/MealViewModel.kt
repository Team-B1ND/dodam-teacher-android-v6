package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.GetMealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.GetMealState
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetCalorieOfMealUseCase
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMealUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val getCalorieOfMealUseCase: GetCalorieOfMealUseCase,
) : ContainerHost<GetMealState, GetMealSideEffect>, ViewModel() {

    override val container = container<GetMealState, GetMealSideEffect>(GetMealState())

    fun getMeal(date: LocalDate) = intent {
        var mealData = Meal(date)
        getMealUseCase(date)
            .onSuccess {
                mealData = mealData.copy(
                    date = it.date,
                    exists = it.exists,
                    breakfast = it.breakfast,
                    lunch = it.lunch,
                    dinner = it.dinner
                )
                postSideEffect(GetMealSideEffect.Toast("breakfast : ${mealData.breakfast}"))
                reduce {
                    state.copy(
                        loading = false,
                        meal = mealData
                    )
                }
            }
            .onFailure { exception ->
                reduce {
                    state.copy(loading = false, exception = exception)
                }
            }
    }
}
