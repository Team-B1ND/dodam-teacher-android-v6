package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.banner.GetActiveBannersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMealUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateLocalUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.timetable.GetTimeTablesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.SetStudyRoomsUseCase

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOutsByDateLocalUseCase: GetOutsByDateLocalUseCase,
    private val getMealUseCase: GetMealUseCase,
    private val getActiveBannersUseCase: GetActiveBannersUseCase,
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getStudyRoomsUseCase: GetStudyRoomsUseCase,
    private val setStudyRoomsUseCase: SetStudyRoomsUseCase,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    init {
        getOutsByDate(LocalDateTime.now())
        getMeal(
            if (LocalDateTime.now().hour >= 20)
                LocalDate.now().plusDays(1)
            else
                LocalDate.now()
        )
        getBanner()
        getStudents()
        getStudyRooms()
    }

    private fun getOutsByDate(date: LocalDateTime) = intent {
        reduce {
            state.copy(isOutLoading = true)
        }

        getOutsByDateLocalUseCase(GetOutsByDateLocalUseCase.Param(date)).onSuccess { out ->
            val outgoingsCnt = out.outgoings.filter { it.status == OutStatus.PENDING }.size
            val outsleepingsCnt = out.outsleepings.filter { it.status == OutStatus.PENDING }.size

            out.outgoings.forEach {
                Log.d("HomeLog", it.status.name)
            }

            reduce {
                state.copy(
                    isOutLoading = false,
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

    private fun getStudyRooms() = intent {
        getStudyRoomsUseCase().onSuccess {
            reduce {
                state.copy(
                    studyRooms = it
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

        getOutsByDateRemoteUseCase(GetOutsByDateRemoteUseCase.Param(LocalDate.now().toString())).onSuccess { out ->
            val outgoingsCnt = out.outgoings.filter { it.status == OutStatus.PENDING }.size
            val outsleepingsCnt = out.outsleepings.filter { it.status == OutStatus.PENDING }.size

            reduce {
                state.copy(
                    outgoingCount = outgoingsCnt,
                    outsleepingCount = outsleepingsCnt,
                    refreshing = false,
                    outRefreshTime = LocalDateTime.now()
                )
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

    fun getStudyRoomRemote() = intent {
        reduce {
            state.copy(refreshing = true)
        }

        val today = LocalDate.now()
        setStudyRoomsUseCase(SetStudyRoomsUseCase.Param(today.year, today.monthValue, today.dayOfMonth)).onSuccess {

            reduce {
                state.copy(
                    studyRooms = it,
                    refreshing = false,
                    studyRoomRefreshTime = LocalDateTime.now()
                )
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

    fun updateCurrentTime(time: LocalDateTime) = intent {
        reduce {
            state.copy(
                outRefreshTime = time
            )
        }
    }
}
