package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplySideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.apply.mvi.ApplyState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ApplyViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
) : ContainerHost<ApplyState, ApplySideEffect>, ViewModel() {

    override val container: Container<ApplyState, ApplySideEffect> = container(ApplyState())

    init {
        getClassrooms()
    }

    private fun getClassrooms() = intent {
        getClassroomsUseCase().onSuccess {
            reduce {
                state.copy(
                    classrooms = it
                )
            }
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
}