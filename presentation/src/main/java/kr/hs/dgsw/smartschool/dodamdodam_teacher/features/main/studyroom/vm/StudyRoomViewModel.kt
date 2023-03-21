package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetStudyRoomsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.timetable.GetTimeTablesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class StudyRoomViewModel @Inject constructor(
    private val getTimeTablesUseCase: GetTimeTablesUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getStudyRoomsUseCase: GetStudyRoomsUseCase,
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container: Container<StudyRoomState, StudyRoomSideEffect> = container(StudyRoomState())

    init {
        getTimeTable()
        getStudents()
        getStudyRooms()
    }

    private fun getTimeTable() = intent {
        getTimeTablesUseCase().onSuccess {
            reduce {
                state.copy(
                    timeTables = it
                )
            }
        }.onFailure {
            postSideEffect(StudyRoomSideEffect.ShowException(it))
        }
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    students = it
                )
            }
        }.onFailure {
            postSideEffect(StudyRoomSideEffect.ShowException(it))
        }
    }

    private fun getStudyRooms() = intent {
        getStudyRoomsUseCase().onSuccess {
            reduce {
                state.copy(
                    studyRooms = it
                )
            }
        }.onFailure {
            postSideEffect(StudyRoomSideEffect.ShowException(it))
        }
    }
}
