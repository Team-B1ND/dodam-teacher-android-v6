package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.point.mvi.PointState
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class PointViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
): ContainerHost<PointState, PointSideEffect>, ViewModel() {

    override val container: Container<PointState, PointSideEffect> = container(PointState())

    init {
        getClassrooms()
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
            postSideEffect(PointSideEffect.FailToGetClassrooms(it))
        }
    }
}