package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutState
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class OutViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
) : ContainerHost<OutState, OutSideEffect>, ViewModel() {

    override val container: Container<OutState, OutSideEffect> = container(OutState())

    init {
        getClassrooms()
        getOutsRemote()
        getMembers()
        getStudents()
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
                    outGoings = it.outsleepings.getNotAllowedOutItems(),
                    outSleepings = it.outgoings.getNotAllowedOutItems(),
                )
            }
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
        getClassroomsUseCase().onSuccess {
            reduce {
                state.copy(
                    classrooms = it
                )
            }
        }.onFailure {
            postSideEffect(OutSideEffect.ShowException(it))
        }
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

    private fun List<OutItem>.getNotAllowedOutItems(): List<OutItem> =
        this.filter {
            it.status == OutStatus.PENDING
        }
}
