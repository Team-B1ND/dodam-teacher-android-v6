package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.contract.MainState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.SetClassroomUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setClassroomUseCase: SetClassroomUseCase
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        setClassroom()
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
}