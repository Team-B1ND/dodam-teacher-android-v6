package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMealUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getMealUseCase: GetMealUseCase,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    init {
        getOutsByDate(LocalDate.now())
        getMeal(
            if (LocalDateTime.now().hour >= 20)
                LocalDate.now().plusDays(1)
            else
                LocalDate.now()
        )
    }

    private fun getOutsByDate(date: LocalDate) = intent {
        reduce {
            state.copy(isOutLoading = true)
        }

        getOutsByDateRemoteUseCase(GetOutsByDateRemoteUseCase.Param(date.toString())).onSuccess { out ->
            val outgoingsCnt = out.outgoings.filter { it.teacherId == null }.size
            val outsleepingsCnt = out.outsleepings.filter { it.teacherId == null }.size

            reduce {
                state.copy(
                    isOutLoading = false,
                    outUpdateDate = LocalDateTime.now(),
                    outgoingCount = outgoingsCnt,
                    outsleepingCount = outsleepingsCnt,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isOutLoading = false,
                )
            }
            postSideEffect(HomeSideEffect.ToastError(it))
        }
    }

    private fun getMeal(date: LocalDate) = intent {
        reduce {
            state.copy(isMealLoading = true)
        }

        getMealUseCase(date).onSuccess {
            reduce {
                state.copy(
                    isMealLoading = false,
                    meal = it,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isMealLoading = false,
                )
            }
            postSideEffect(HomeSideEffect.ToastError(it))
        }
    }
}
