package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplyState
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CheckStudyRoomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.SetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.UnCheckStudyRoomUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ApplyViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val setStudyRoomsUseCase: SetStudyRoomsUseCase,
    private val checkStudyRoomUseCase: CheckStudyRoomUseCase,
    private val unCheckStudyRoomUseCase: UnCheckStudyRoomUseCase,
) : ContainerHost<ApplyState, ApplySideEffect>, ViewModel() {

    override val container: Container<ApplyState, ApplySideEffect> = container(ApplyState())

    init {
        getClassrooms()
        getStudents()
        getMembers()
        val today = LocalDate.now()
        setStudyRooms(today.year, today.monthValue, today.dayOfMonth)
    }

    private fun setStudyRooms(year: Int, month: Int, day: Int) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        setStudyRoomsUseCase(SetStudyRoomsUseCase.Param(year, month, day)).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                    studyRooms = it,
                )
            }
            if (state.members.isNotEmpty() && state.students.isNotEmpty() && state.classrooms.isNotEmpty())
                makeApplyItemList()
        }.onFailure {
            reduce {
                state.copy(
                    loading = false
                )
            }
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    fun getStudyRoomRefresh() = intent {
        reduce {
            state.copy(
                refreshing = true,
            )
        }

        val today = LocalDate.now()

        setStudyRoomsUseCase(SetStudyRoomsUseCase.Param(today.year, today.monthValue, today.dayOfMonth)).onSuccess {
            reduce {
                state.copy(
                    refreshing = false,
                    studyRooms = it,
                )
            }
            if (state.members.isNotEmpty() && state.students.isNotEmpty() && state.classrooms.isNotEmpty())
                makeApplyItemList()
        }.onFailure {
            reduce {
                state.copy(
                    refreshing = false,
                )
            }
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    fun checkStudyRoom(id: Int) = intent {
        reduce {
            state.copy(
                loading = true,
            )
        }
        checkStudyRoomUseCase(id).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            val today = LocalDate.now()
            setStudyRooms(today.year, today.monthValue, today.dayOfMonth)
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    fun unCheckStudyRoom(id: Int) = intent {
        reduce {
            state.copy(
                loading = true,
            )
        }
        unCheckStudyRoomUseCase(id).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            val today = LocalDate.now()
            setStudyRooms(today.year, today.monthValue, today.dayOfMonth)
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    private fun getClassrooms() = intent {
        getClassroomsUseCase().onSuccess {
            reduce {
                state.copy(
                    classrooms = it
                )
            }
            if (state.studyRooms != null && state.students.isNotEmpty() && state.members.isNotEmpty())
                makeApplyItemList()
        }.onFailure {
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    students = it
                )
            }
            if (state.members.isNotEmpty() && state.studyRooms != null && state.classrooms.isNotEmpty())
                makeApplyItemList()
        }.onFailure {
            postSideEffect(ApplySideEffect.ShowException(it))
        }
    }

    private fun getMembers() = intent {
        getMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    members = it.filter { it.role == MemberRole.STUDENT }
                )
            }
            if (state.studyRooms != null && state.students.isNotEmpty() && state.classrooms.isNotEmpty())
                makeApplyItemList()
        }.onFailure {
            postSideEffect(ApplySideEffect.ShowException(it))
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

    fun updateOutType(applyType: Int) = intent {
        reduce {
            state.copy(
                currentApplyType = applyType
            )
        }
    }

    fun updateSelectedItem(selectedItem: ApplyState.ApplyItem?) = intent {
        reduce {
            state.copy(
                currentSelectedItem = selectedItem
            )
        }
    }

    private fun makeApplyItemList() = intent {
        val applyItemList = emptyList<ApplyState.ApplyItem>().toMutableList()
        state.studyRooms?.forEach { studyRoom ->
            val student = state.students.find { it.id == studyRoom.studentId }
            val member = state.members.find { it.id == student?.member?.id }
            val classroom = state.classrooms.find { it.id == student?.classroom?.id }

            if (student != null && member != null && classroom != null) {
                applyItemList.add(
                    ApplyState.ApplyItem(
                        student = student,
                        member = member,
                        studyRoom = studyRoom,
                        classroom = classroom,
                    )
                )
            }
        }

        reduce {
            state.copy(
                applyItemList = applyItemList
            )
        }
    }
}
