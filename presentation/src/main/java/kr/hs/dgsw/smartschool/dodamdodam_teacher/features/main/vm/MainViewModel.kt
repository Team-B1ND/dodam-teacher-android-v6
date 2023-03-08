package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.SetClassroomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.SetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.SetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.teacher.SetTeachersUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setClassroomUseCase: SetClassroomUseCase,
    private val setMembersUseCase: SetMembersUseCase,
    private val setStudentsUseCase: SetStudentsUseCase,
    private val setTeachersUseCase: SetTeachersUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        setClassroom()
        setMembers()
        setTeachers()
        setStudents()
    }

    private fun setClassroom() = intent {
        reduce {
            state.copy(
                setClassroomLoading = true
            )
        }

        setClassroomUseCase().onSuccess {
            reduce {
                state.copy(
                    setClassroomLoading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    setClassroomLoading = false
                )
            }
        }
    }

    private fun setMembers() = intent {
        reduce {
            state.copy(
                setMembersLoading = true
            )
        }

        setMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    setMembersLoading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    setMembersLoading = false
                )
            }
        }
    }

    private fun setStudents() = intent {
        reduce {
            state.copy(
                setStudentsLoading = true
            )
        }

        setStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    setStudentsLoading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    setStudentsLoading = false
                )
            }
        }
    }

    private fun setTeachers() = intent {
        reduce {
            state.copy(
                setTeachersLoading = true
            )
        }

        setTeachersUseCase().onSuccess {
            reduce {
                state.copy(
                    setTeachersLoading = false
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    setTeachersLoading = false
                )
            }
        }
    }
}
