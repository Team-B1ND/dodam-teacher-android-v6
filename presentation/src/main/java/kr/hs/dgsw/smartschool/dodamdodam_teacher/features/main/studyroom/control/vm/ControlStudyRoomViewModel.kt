package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomState
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CtrlStudyRoomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.teacher.GetMyInfoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ControlStudyRoomViewModel @Inject constructor(
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
) : ContainerHost<ControlStudyRoomState, ControlStudyRoomSideEffect>, ViewModel() {

    override val container: Container<ControlStudyRoomState, ControlStudyRoomSideEffect> = container(
        ControlStudyRoomState()
    )

    init {
        getMyInfo()
    }

    fun ctrlStudyRoom(studentId: Int, studyRoomList: List<StudyRoomItem>) = intent {
        reduce {
            state.copy(
                ctrlStudyRoomLoading = true
            )
        }

        ctrlStudyRoomUseCase(
            CtrlStudyRoomUseCase.Param(
                studentId = studentId,
                studyRoomList = studyRoomList,
            )
        ).onSuccess {
            reduce {
                state.copy(
                    ctrlStudyRoomLoading = false,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    ctrlStudyRoomLoading = false,
                )
            }
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }

    private fun getMyInfo() = intent {
        reduce {
            state.copy(
                getMyInfoLoading = true,
            )
        }

        getMyInfoUseCase().onSuccess {
            reduce {
                state.copy(
                    getMyInfoLoading = false,
                    myInfo = it,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    getMyInfoLoading = false,
                )
            }
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }
}