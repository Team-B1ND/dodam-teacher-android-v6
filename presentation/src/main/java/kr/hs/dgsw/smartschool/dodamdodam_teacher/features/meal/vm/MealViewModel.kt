package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.MealSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.meal.mvi.MealState
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
) : ContainerHost<MealState, MealSideEffect>, ViewModel() {

    override val container = container<MealState, MealSideEffect>(MealState())

    fun getMeal(date: LocalDate) = intent {
        getMealUseCase(date)
            .onSuccess {
                reduce {
                    state.copy(
                        loading = false,
                        meal = it
                    )
                }
            }
            .onFailure { exception ->
                reduce {
                    state.copy(loading = false)
                }
                postSideEffect(MealSideEffect.ToastError(exception))
            }
    }

    fun plusDay() = intent {
        reduce {
            state.copy(
                currentDate = state.currentDate.plusDays(1)
            )
        }
    }

    fun minusDay() = intent {
        reduce {
            state.copy(
                currentDate = state.currentDate.minusDays(1)
            )
        }
    }

    fun updateDate(date: LocalDate) = intent {
        reduce {
            state.copy(
                currentDate = date
            )
        }
    }

    fun changeShowDialogState() = intent {
        reduce {
            state.copy(
                showDialog = state.showDialog.not()
            )
        }
    }
}
