package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.mvi.CurrentOutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.mvi.CurrentOutState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.out.screen.getNextDay
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.CancelAllowOutgoingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.CancelAllowOutsleepingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CurrentOutViewModel @Inject constructor(
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val cancelAllowOutgoingUseCase: CancelAllowOutgoingUseCase,
    private val cancelAllowOutsleepingUseCase: CancelAllowOutsleepingUseCase,

) : ContainerHost<CurrentOutState, CurrentOutSideEffect>, ViewModel() {

    override val container: Container<CurrentOutState, CurrentOutSideEffect> = container(CurrentOutState())

    private val getDate = getNextDay()

    init {
        getClassrooms()
        getOutsRemote()
        getMembers()
        getStudents()
        getOutSleepingRemote()
    }

    fun getOutsRemote() = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }

        getOutsByDateRemoteUseCase(
            GetOutsByDateRemoteUseCase.Param(
                date = getDate
            )
        ).onSuccess {
            reduce {
                state.copy(
                    getOutsLoading = false,
                    outGoings = it.filter { it.status == OutStatus.ALLOWED },
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    fun getOutSleepingRemote() = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }

        getOutsByDateRemoteUseCase.getOutSleepingValid(
        ).onSuccess {
            reduce {
                state.copy(
                    getOutsLoading = false,
                    outSleepings = it.filter { it.status == OutStatus.ALLOWED },
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    fun getOutsRefresh() = intent {
        reduce {
            state.copy(
                refreshing = true,
            )
        }

        getOutsByDateRemoteUseCase(
            GetOutsByDateRemoteUseCase.Param(
                date = getDate
            )
        ).onSuccess {outGoing ->
            getOutsByDateRemoteUseCase.getOutSleepingValid(
            ).onSuccess {outSleeping ->
                reduce {
                    state.copy(
                        refreshing = false,
                        outGoings = outGoing,
                        outSleepings = outSleeping,
                    )
                }
            }

        }.onFailure {
            reduce {
                state.copy(
                    refreshing = false,
                )
            }
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    fun cancelAllowOutgoing(id: Int) = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }
        cancelAllowOutgoingUseCase(
            CancelAllowOutgoingUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(CurrentOutSideEffect.SuccessControl("외출 취소에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    fun cancelAllowOutsleeping(id: Int) = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }
        cancelAllowOutsleepingUseCase(
            CancelAllowOutsleepingUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(CurrentOutSideEffect.SuccessControl("외박 취소에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    private fun getClassrooms() = intent {
//        getClassroomsUseCase().onSuccess {
//            reduce {
//                state.copy(
//                    classrooms = it
//                )
//            }
//        }.onFailure {
//            postSideEffect(CurrentOutSideEffect.ShowException(it))
//        }
    }

    private fun getStudents() = intent {
//        getStudentsUseCase().onSuccess {
//            reduce {
//                state.copy(
//                    students = it
//                )
//            }
//        }.onFailure {
//            postSideEffect(CurrentOutSideEffect.ShowException(it))
//        }
    }

    private fun getMembers() = intent {
        getMembersUseCase().onSuccess {


            reduce {
                state.copy(
                    members = it
                )
            }
        }.onFailure {
            Log.d("TAG", "실패: ")
            postSideEffect(CurrentOutSideEffect.ShowException(it))
        }
    }

    fun updateShowPrompt(showPrompt: Boolean) = intent {
        reduce {
            state.copy(
                showPrompt = showPrompt
            )
        }
    }

    fun updateGrade(grade: Int) = intent {
        reduce {
            state.copy(
                currentGrade = grade
            )
        }
    }

    fun updateClassroom(classroom: Int) = intent {
        reduce {
            state.copy(
                currentClassroom = classroom
            )
        }
    }

    fun updateOutType(outType: Int) = intent {
        reduce {
            state.copy(
                currentOutType = outType
            )
        }
    }

    fun updateOutItem(outItem: Out) = intent {
        reduce {
            state.copy(
                currentSelectedOutItem = outItem
            )
        }
    }

    private fun List<OutItem>.getAllowedOutItem(): List<OutItem> =
        this.filter {
            it.status == OutStatus.ALLOWED
        }
}
