package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class PointViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
): ContainerHost<PointState, PointSideEffect>, ViewModel() {

    override val container: Container<PointState, PointSideEffect> = container(PointState())

    init {
        getClassrooms()
        getMembers()
        getStudents()
    }

    fun updatePage(page: Int) = intent {
        reduce {
            state.copy(
                page = page
            )
        }
    }

    private fun getClassrooms() = intent {
        getClassroomsUseCase().onSuccess {
            reduce {
                state.copy(
                    classrooms = it
                )
            }
            if (state.members.isNotEmpty() && state.classrooms.isNotEmpty()) {
                makePointStudents()
            }
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    students = it
                )
            }
            if (state.members.isNotEmpty() && state.classrooms.isNotEmpty())
                makePointStudents()
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    private fun getMembers() = intent {
        getMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    members = it.filter { it.role == MemberRole.STUDENT }
                )
            }
            if (state.students.isNotEmpty() && state.classrooms.isNotEmpty())
                makePointStudents()
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    fun updateChecked(id: String) = intent {
        reduce {
            state.copy(
                pointStudents = state.pointStudents.toMutableList().map {
                    if (id == it.id)
                        PointState.PointStudent(
                            id = it.id,
                            name = it.name,
                            grade = it.grade,
                            room = it.room,
                            isChecked =  it.isChecked.not()
                        )
                    else
                        it
                }
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

    private fun makePointStudents() = intent {
        reduce {
            val list = emptyList<PointState.PointStudent>().toMutableList()
            state.students.map { student ->
                state.members.forEach { member ->
                    state.classrooms.forEach { classroom ->
                        if (student.member.id == member.id) {
                            if (student.classroom.id == classroom.id) {
                                list.add(
                                    PointState.PointStudent(
                                        id = member.id,
                                        name = member.name,
                                        grade = classroom.grade,
                                        room = classroom.room,
                                        isChecked = false,
                                    )
                                )
                            }
                        }
                    }
                }
            }
            state.copy(
                pointStudents = list
            )
        }
    }
}