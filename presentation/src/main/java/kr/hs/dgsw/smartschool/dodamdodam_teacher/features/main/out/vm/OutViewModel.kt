package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutState
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.AllowOutgoingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.AllowOutsleepingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.CancelAllowOutgoingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.CancelAllowOutsleepingUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateLocalUseCase
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
class OutViewModel @Inject constructor(
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getOutsByDateLocalUseCase: GetOutsByDateLocalUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetMembersUseCase,
    private val allowOutgoingUseCase: AllowOutgoingUseCase,
    private val cancelAllowOutgoingUseCase: CancelAllowOutgoingUseCase,
    private val allowOutsleepingUseCase: AllowOutsleepingUseCase,
    private val cancelAllowOutsleepingUseCase: CancelAllowOutsleepingUseCase,
) : ContainerHost<OutState, OutSideEffect>, ViewModel() {

    override val container: Container<OutState, OutSideEffect> = container(OutState())

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
                date = LocalDate.now().toString()
            )
        ).onSuccess {
            reduce {
                state.copy(
                    getOutsLoading = false,
                    outGoings = it.filter { it.status == OutStatus.PENDING },
                )
            }
        }.onFailure {
            getOutsByDateLocalUseCase(
                GetOutsByDateLocalUseCase.Param(
                    LocalDate.now()
                )
            ).onSuccess {
                reduce {
                    state.copy(
                        getOutsLoading = false,
                        outGoings = it.filter { it.status == OutStatus.PENDING },
                    )
                }
            }
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun getOutSleepingRemote() = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }

        getOutsByDateRemoteUseCase.getOutSleeping(
            GetOutsByDateRemoteUseCase.Param(
                LocalDate.now().toString()
            )
        ).onSuccess {
            reduce {
                state.copy(
                    getOutsLoading = false,
                    outSleepings = it.filter { it.status == OutStatus.PENDING },
                )
            }
        }.onFailure {
            getOutsByDateLocalUseCase.getOutSleeping(
                GetOutsByDateLocalUseCase.Param(
                    LocalDate.now()
                )
            ).onSuccess {
                reduce {
                    state.copy(
                        getOutsLoading = false,
                        outSleepings = it.filter { it.status == OutStatus.PENDING },
                    )
                }
            }
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
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
                date = LocalDate.now().toString()
            )
        ).onSuccess {
            reduce {
                state.copy(
                    refreshing = false,
                    outGoings = it.filter { it.status == OutStatus.PENDING },
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    refreshing = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun getOutSleepingRefresh() = intent {
        reduce {
            state.copy(
                refreshing = true,
            )
        }

        getOutsByDateRemoteUseCase.getOutSleeping(
            GetOutsByDateRemoteUseCase.Param(
                date = LocalDate.now().toString()
            )
        ).onSuccess {
            reduce {
                state.copy(
                    refreshing = false,
                    outSleepings = it.filter { it.status == OutStatus.PENDING },
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    refreshing = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun allowOutgoing(id: Int) = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }
        allowOutgoingUseCase(
            AllowOutgoingUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(OutSideEffect.SuccessControl("외출 승인에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun allowOutsleeping(id: Int) = intent {
        reduce {
            state.copy(
                getOutsLoading = true,
            )
        }
        allowOutsleepingUseCase(
            AllowOutsleepingUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(OutSideEffect.SuccessControl("외박 승인에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun denyOutgoing(id: Int) = intent {
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
            postSideEffect(OutSideEffect.SuccessControl("외출 거절에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    fun denyOutsleeping(id: Int) = intent {
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
            postSideEffect(OutSideEffect.SuccessControl("외박 거절에 성공했어요"))
        }.onFailure {
            reduce {
                state.copy(
                    getOutsLoading = false,
                )
            }
            postSideEffect(OutSideEffect.ShowException(it))
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
//            postSideEffect(OutSideEffect.ShowException(it))
//        }
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    students = it
                )
            }
        }.onFailure {
            postSideEffect(OutSideEffect.ShowException(it))
        }
    }

    private fun getMembers() = intent {
        getMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    members = it.filter { it.role == MemberRole.STUDENT }
                )
            }
        }.onFailure {
            postSideEffect(OutSideEffect.ShowException(it))
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
}
