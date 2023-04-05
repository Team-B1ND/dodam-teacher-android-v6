package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.SetClassroomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.SetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.SetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.SetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.teacher.SetTeachersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.timetable.SetTimeTablesUseCase
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
class MainViewModel @Inject constructor(
    private val setClassroomUseCase: SetClassroomUseCase,
    private val setMembersUseCase: SetMembersUseCase,
    private val setStudentsUseCase: SetStudentsUseCase,
    private val setTeachersUseCase: SetTeachersUseCase,
    private val setTimeTablesUseCase: SetTimeTablesUseCase,
    private val setStudyRoomsUseCase: SetStudyRoomsUseCase,
    private val getOutsByDateRemoteUseCase: GetOutsByDateRemoteUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        setClassroom()
        setMembers()
        setTeachers()
        setStudents()
        setOuts()
        setStudyRooms()
        setTimeTables()
    }

    private fun setStudyRooms() = intent {
        reduce {
            state.copy(
                setStudyRoomsLoading = true
            )
        }

        val today = LocalDate.now()
        setStudyRoomsUseCase(
            SetStudyRoomsUseCase.Param(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
            )
        ).onSuccess {
            reduce {
                state.copy(
                    getStudyRoomTime = LocalDateTime.now(),
                    setStudyRoomsLoading = false
                )
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ShowException(it))
            reduce {
                state.copy(
                    setStudyRoomsLoading = false
                )
            }
        }
    }

    private fun setTimeTables() = intent {
        reduce {
            state.copy(
                setTimeTablesLoading = true
            )
        }

        setTimeTablesUseCase().onSuccess {
            reduce {
                state.copy(
                    setTimeTablesLoading = false
                )
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ShowException(it))
            reduce {
                state.copy(
                    setTimeTablesLoading = false
                )
            }
        }
    }

    private fun setOuts() = intent {
        reduce {
            state.copy(
                setOutsLoading = true
            )
        }

        getOutsByDateRemoteUseCase(
            GetOutsByDateRemoteUseCase.Param(date = LocalDate.now().toString())
        ).onSuccess {
            reduce {
                state.copy(
                    getOutTime = LocalDateTime.now(),
                    setOutsLoading = false
                )
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ShowException(it))
            reduce {
                state.copy(
                    setOutsLoading = false
                )
            }
        }
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
            postSideEffect(MainSideEffect.ShowException(it))
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
            postSideEffect(MainSideEffect.ShowException(it))
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
            postSideEffect(MainSideEffect.ShowException(it))
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
            postSideEffect(MainSideEffect.ShowException(it))
            reduce {
                state.copy(
                    setTeachersLoading = false
                )
            }
        }
    }

    fun updateSelectedTab(tab: Int) = intent {
        reduce {
            state.copy(
                selectedTab = tab
            )
        }
    }
}
