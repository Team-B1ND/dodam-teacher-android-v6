package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.mvi.CurrentNightStudySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.night_study.current.mvi.CurrentNightStudyState
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudy
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMembersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.night_study.DenyNightStudyUseCase
import kr.hs.dgsw.smartschool.domain.usecase.night_study.GetNightStudyUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CurrentNightStudyViewModel @Inject constructor(
    private val getNightStudyUseCase: GetNightStudyUseCase,
    private val denyNightStudyUseCase: DenyNightStudyUseCase,
    private val getMembersUseCase: GetMembersUseCase,
) : ContainerHost<CurrentNightStudyState, CurrentNightStudySideEffect>, ViewModel() {

    override val container: Container<CurrentNightStudyState, CurrentNightStudySideEffect> = container(CurrentNightStudyState())

    init {
        getCurrentNightStudy()
        getClassrooms()
        getMembers()
//        getStudents()
    }

    fun getCurrentNightStudy() = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }

        getNightStudyUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        isLoading = false,
                        nightStudies = it,
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
                postSideEffect(CurrentNightStudySideEffect.ShowException(it))
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
            postSideEffect(CurrentNightStudySideEffect.SuccessControl("심자 거절을 성공했어요!"))
        }.onFailure {
            reduce {
                state.copy(
                    isLoading = false
                )
            }
            postSideEffect(CurrentNightStudySideEffect.ShowException(it))
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
                reduce {
                    state.copy(
                        refreshing = false,
                        nightStudies = it,
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        refreshing = false,
                    )
                }
                postSideEffect(CurrentNightStudySideEffect.ShowException(it))
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
//            postSideEffect(CurrentNightStudySideEffect.ShowException(it))
//        }
    }

//    private fun getStudents() = intent {
//        getStudentsUseCase().onSuccess {
//            reduce {
//                state.copy(
//                    students = it
//                )
//            }
//        }.onFailure {
//            postSideEffect(CurrentNightStudySideEffect.ShowException(it))
//        }
//    }

    private fun getMembers() = intent {
        getMembersUseCase().onSuccess {
            reduce {
                state.copy(
                    members = it.filter { it.role == MemberRole.STUDENT }
                )
            }
        }.onFailure {
            postSideEffect(CurrentNightStudySideEffect.ShowException(it))
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
