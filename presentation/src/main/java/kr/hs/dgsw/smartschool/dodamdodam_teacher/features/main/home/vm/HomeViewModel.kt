package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.banner.GetActiveBannersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMealUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateLocalUseCase
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
    private val getOutsByDateLocalUseCase: GetOutsByDateLocalUseCase,
    private val getMealUseCase: GetMealUseCase,
    private val getActiveBannersUseCase: GetActiveBannersUseCase,
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getStudentsUseCase: GetMembersUseCase,
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
        getBanner()
        getStudents()
    }

    private fun getOutsByDate(date: LocalDate) = intent {
        reduce {
            state.copy(isOutLoading = true)
        }

        getOutsByDateRemoteUseCase(
            GetOutsByDateRemoteUseCase.Param(
                date.toString()
            )
        ).onSuccess { outGoing ->
            getOutsByDateRemoteUseCase.getOutSleeping(
                GetOutsByDateRemoteUseCase.Param(
                    date.toString()
                )
            ).onSuccess { outSleeping ->
                val outgoingsCnt = outGoing.filter { it.status == OutStatus.PENDING }.size
                val outSleepingCnt = outSleeping.filter { it.status == OutStatus.PENDING }.size

                reduce {
                    state.copy(
                        outgoingCount = outgoingsCnt,
                        outsleepingCount = outSleepingCnt,
                        refreshing = false,
                        outRefreshTime = LocalDateTime.now()
                    )
                }
            }
        }
            .onFailure {
                reduce {
                    state.copy(
                        isOutLoading = false,
                    )
                }
                postSideEffect(HomeSideEffect.ToastError(it))
            }
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    allStudentsCount = it.size,
                )
            }
        }.onFailure {
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

    fun getOutRemote() = intent {
        reduce {
            state.copy(refreshing = true)
        }

        getOutsByDateRemoteUseCase(
            GetOutsByDateRemoteUseCase.Param(
                LocalDate.now().toString()
            )
        ).onSuccess { outGoing ->
            getOutsByDateRemoteUseCase.getOutSleeping(
                GetOutsByDateRemoteUseCase.Param(
                    LocalDate.now().toString()
                )
            ).onSuccess { outSleeping ->
                val outgoingsCnt = outGoing.filter { it.status == OutStatus.PENDING }.size
                val outSleepingCnt = outSleeping.filter { it.status == OutStatus.PENDING }.size

                reduce {
                    state.copy(
                        outgoingCount = outgoingsCnt,
                        outsleepingCount = outSleepingCnt,
                        refreshing = false,
                        outRefreshTime = LocalDateTime.now()
                    )
                }
            }
        }.onFailure {
            reduce {
                state.copy(
                    refreshing = false,
                )
            }
            postSideEffect(HomeSideEffect.ToastError(it))
        }
    }

    private fun getBanner() = intent {
        getActiveBannersUseCase(true).onSuccess {
            reduce {
                state.copy(banners = it)
            }
        }.onFailure {
            postSideEffect(HomeSideEffect.ToastError(it))
        }
    }
}
