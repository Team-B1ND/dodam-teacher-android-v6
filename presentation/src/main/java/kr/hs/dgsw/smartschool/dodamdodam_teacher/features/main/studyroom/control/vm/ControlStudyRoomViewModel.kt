package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.control.mvi.ControlStudyRoomState
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.usecase.place.GetPlacesUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CtrlStudyRoomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.SetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.teacher.GetMyInfoUseCase
import kr.hs.dgsw.smartschool.domain.usecase.timetable.GetTimeTablesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ControlStudyRoomViewModel @Inject constructor(
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getStudyRoomsUseCase: GetStudyRoomsUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getTimeTablesUseCase: GetTimeTablesUseCase,
    private val setStudyRoomsUseCase: SetStudyRoomsUseCase,
) : ContainerHost<ControlStudyRoomState, ControlStudyRoomSideEffect>, ViewModel() {

    override val container: Container<ControlStudyRoomState, ControlStudyRoomSideEffect> = container(
        ControlStudyRoomState()
    )

    init {
        getMyInfo()
        getPlaces()
        getTimeTables()
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
            setStudyRooms()
        }.onFailure {
            reduce {
                state.copy(
                    ctrlStudyRoomLoading = false,
                )
            }
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }

    private fun setStudyRooms() = intent {
        reduce {
            state.copy(
                ctrlStudyRoomLoading = true
            )
        }

        val today = LocalDate.now()

        setStudyRoomsUseCase(
            SetStudyRoomsUseCase.Param(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
            )
        ).onSuccess {
            reduce {
                state.copy(
                    ctrlStudyRoomLoading = false,
                )
            }
            postSideEffect(ControlStudyRoomSideEffect.SuccessCtrl)
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

    private fun getPlaces() = intent {
        getPlacesUseCase().onSuccess {
            reduce {
                state.copy(
                    places = it,
                )
            }
        }.onFailure {
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }

    private fun getTimeTables() = intent {
        getTimeTablesUseCase().onSuccess {
            reduce {
                state.copy(
                    timeTables = it,
                )
            }
        }.onFailure {
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }

    @Suppress("Unused")
    fun getStudyRooms(studentId: Int) = intent {
        Log.d("CheckFunction", "CHECKCHECK")
        getStudyRoomsUseCase().onSuccess {
            val myStudyRooms = it.filter { studyRoom -> studyRoom.studentId == studentId }
            reduce {
                state.copy(
                    myStudyRooms = myStudyRooms
                )
            }
            myStudyRooms.forEach { studyRoom ->
                updateSelectedStudyRoom(
                    timeTableId = studyRoom.timeTable.id,
                    studyRoomItem = StudyRoomItem(
                        placeId = studyRoom.place.id,
                        timeTableId = studyRoom.timeTable.id,
                    )
                )
            }
        }.onFailure {
            postSideEffect(ControlStudyRoomSideEffect.ShowException(it))
        }
    }

    fun updateSelectedStudyRoom(timeTableId: Int, studyRoomItem: StudyRoomItem) = intent {
        reduce {
            state.copy(
                selectedStudyRoom = state.selectedStudyRoom.toMutableMap().plus(timeTableId to studyRoomItem)
            )
        }
    }
}
