package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.out.mvi.OutState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class OutViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
) : ContainerHost<OutState, OutSideEffect>, ViewModel() {

    override val container: Container<OutState, OutSideEffect> = container(OutState())

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
}
