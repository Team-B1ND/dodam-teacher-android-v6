package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.mvi.NightStudySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.mvi.NightStudyState
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudy
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.night_study.AllowNightStudyUseCase
import kr.hs.dgsw.smartschool.domain.usecase.night_study.DenyNightStudyUseCase
import kr.hs.dgsw.smartschool.domain.usecase.night_study.GetNightStudyUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NightStudyViewModel @Inject constructor(
    private val getNightStudyUseCase: GetNightStudyUseCase,
    private val allowNightStudyUseCase: AllowNightStudyUseCase,
    private val denyNightStudyUseCase: DenyNightStudyUseCase,
    private val getMembersUseCase: GetMembersUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
) : ContainerHost<NightStudyState, NightStudySideEffect>, ViewModel() {

    override val container: Container<NightStudyState, NightStudySideEffect> =
        container(NightStudyState())

    init {
        getNightStudy()
        getClassrooms()
        getMembers()
        getStudents()
    }

    fun getNightStudy() = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }

        getNightStudyUseCase()
            .onSuccess {
                Log.d("TAG", "성공: ${it} ")
                reduce {
                    state.copy(
                        isLoading = false,
                        nightStudies = it,
                    )
                }
            }.onFailure {
                Log.d("TAG", "실패:$it ")

                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
                postSideEffect(NightStudySideEffect.ShowException(it))
            }
    }

    fun allowNightStudy(id: Int) = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }

        allowNightStudyUseCase(
            AllowNightStudyUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(NightStudySideEffect.SuccessControl("심자 승인을 성공했어요!"))
        }.onFailure {
            reduce {
                state.copy(
                    isLoading = false
                )
            }
            postSideEffect(NightStudySideEffect.ShowException(it))
        }
    }

    fun denyNightStudy(id: Int) = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }

        denyNightStudyUseCase(
            DenyNightStudyUseCase.Param(
                id = id
            )
        ).onSuccess {
            postSideEffect(NightStudySideEffect.SuccessControl("심자 거절을 성공했어요!"))
        }.onFailure {
            reduce {
                state.copy(
                    isLoading = false
                )
            }
            postSideEffect(NightStudySideEffect.ShowException(it))
        }
    }

    fun getOutsRefresh() = intent {
        reduce {
            state.copy(
                refreshing = true,
            )
        }

        getNightStudyUseCase()
            .onSuccess {
                Log.d("TAG", "getOutsRefresh: 성공")
                reduce {
                    state.copy(
                        refreshing = false,
                        nightStudies = it.filter { it.state == OutStatus.PENDING },
                    )
                }
            }.onFailure {
                Log.d("TAG", "getOutsRefresh: 실패 $it")

                reduce {
                    state.copy(
                        refreshing = false,
                    )
                }
                postSideEffect(NightStudySideEffect.ShowException(it))
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
//            postSideEffect(NightStudySideEffect.ShowException(it))
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
            postSideEffect(NightStudySideEffect.ShowException(it))
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
            postSideEffect(NightStudySideEffect.ShowException(it))
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

    fun updateNightStudy(nightStudy: NightStudy) = intent {
        reduce {
            state.copy(
                currentSelectedNightStudy = nightStudy
            )
        }
    }
}
