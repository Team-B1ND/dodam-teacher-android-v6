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
): ContainerHost<PointState, PointSideEffect>, ViewModel() {

    override val container: Container<PointState, PointSideEffect> = container(PointState())

    init {
        getClassrooms()
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
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    private fun getStudents() = intent {
        getMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    students = it.filter { it.role == MemberRole.STUDENT }.map {
                        PointState.PointStudent(member = it)
                    }
                )
            }
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    fun updateChecked(member: Member) = intent {
        reduce {
            state.copy(
                students = state.students.toMutableList().map {
                    if (member == it.member)
                        PointState.PointStudent(member, it.isChecked.not())
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
                currentGrade = classroom
            )
        }
    }
}