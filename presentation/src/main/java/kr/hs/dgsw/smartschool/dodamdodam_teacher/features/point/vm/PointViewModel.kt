package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.point.GetPointReasonUseCase
import kr.hs.dgsw.smartschool.domain.usecase.point.GivePointUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getPointReasonUseCase: GetPointReasonUseCase,
    private val givePointUseCase: GivePointUseCase,
) : ContainerHost<PointState, PointSideEffect>, ViewModel() {

    override val container: Container<PointState, PointSideEffect> = container(PointState())

    init {
        getClassrooms()
        getMembers()
        getStudents()
        getPointReason(PointType.MINUS)
        getPointReason(PointType.BONUS)
    }

    fun updatePage(page: Int) = intent {
        reduce {
            state.copy(
                page = page
            )
        }
    }

    private fun getClassrooms() = intent {
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
            Log.d("TAG", "getMembers: $it")
            reduce {
                state.copy(
                    members = it.filter {
                        Log.d("TAG", "getMembers: ${it.role == MemberRole.STUDENT}")
                        it.role == MemberRole.STUDENT
                    }
                )
            }
            delay(1000)
            if (state.students.isNotEmpty())
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
                            isChecked = it.isChecked.not(),
                            studentId = it.studentId,
                            profileImage = it.profileImage,
                        )
                    else
                        it
                }
            )
        }
    }

    private fun getPointReason(pointType: PointType) = intent {
        getPointReasonUseCase(pointType).onSuccess {
            reduce {
                if (pointType == PointType.MINUS) {
                    state.copy(
                        minusReason = it
                    )
                } else {
                    state.copy(
                        bonusReason = it
                    )
                }
            }
        }.onFailure {
            postSideEffect(PointSideEffect.ShowException(it))
        }
    }

    fun givePoint(
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType
    ) = intent {
        reduce {
            state.copy(
                givePointLoading = true
            )
        }
        givePointUseCase(
            param = GivePointUseCase.Param(
                givenDate = LocalDate.now().toString(),
                place = place,
                reason = reason,
                score = score,
                studentId = studentId,
                type = type
            )
        ).onSuccess {
            reduce {
                state.copy(
                    givePointLoading = false
                )
            }
            postSideEffect(PointSideEffect.SuccessGivePoint)
        }.onFailure {
            reduce {
                state.copy(
                    givePointLoading = false
                )
            }
            postSideEffect(PointSideEffect.ShowException(it))
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

    fun updateCurrentPlace(place: Int) = intent {
        reduce {
            state.copy(
                currentPlace = place
            )
        }
    }

    fun updateCurrentPointType(pointType: Int) = intent {
        reduce {
            state.copy(
                currentPointType = pointType
            )
        }
    }

    fun updateReason(pointReason: PointReason) = intent {
        reduce {
            state.copy(
                currentSelectedReason = pointReason
            )
        }
    }

    private fun makePointStudents() = intent {
        reduce {
            val list = emptyList<PointState.PointStudent>().toMutableList()
            Log.d("TAG", "makePointStudents: ${state.members}")
            Log.d("TAG", "makePointStudents: ${state.students}")
            state.students.map { student ->
                state.members.forEach { member ->
                    Log.d("TAG", "makePointStudents: ${student.member.id} == ${member.id}")
                    if (student.member.id == member.id) {
                        list.add(
                            PointState.PointStudent(
                                id = member.id,
                                name = member.name,
                                grade = 0,
                                room = 0,
                                isChecked = false,
                                studentId = student.id,
                                profileImage = member.profileImage ?: ""
                            )
                        )
                    }
                }
            }
            state.copy(
                pointStudents = list
            )
        }
    }
}
