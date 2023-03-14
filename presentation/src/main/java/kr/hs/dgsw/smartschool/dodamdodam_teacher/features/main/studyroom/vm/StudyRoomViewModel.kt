package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeSet
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StudyRoomViewModel @Inject constructor(
    private val getAllSheetUseCase: GetAllSheetUseCase,
    private val getSheetByTimeUseCase: GetSheetByTimeUseCase,
    private val checkStudyRoomUseCase: CheckStudyRoomUseCase,
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getStudentsUseCase: GetStudentsUseCase
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container: Container<StudyRoomState, StudyRoomSideEffect> = container(
        StudyRoomState()
    )

    private var totalStudent = 0
    init {
        getStudents()
        getStudyRoomSheet()
    }

    private fun getStudents() = intent {
        getStudentsUseCase().onSuccess {
            totalStudent = it.size
        }.onFailure {
            postSideEffect(StudyRoomSideEffect.ToastError(it))
        }
    }

    fun getStudyRoomSheet() = intent {
        reduce {
            state.copy(loading = true)
        }

        getAllSheetUseCase().onSuccess { studyRoomResult ->
            val isWeekDay : Boolean = studyRoomResult.studyRoomList.get(0).timeTable.type == TimeTableType.WEEKDAY

            val firstClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == if(isWeekDay) TimeSet.WeekDay.first_start else TimeSet.WeekEnd.first_start}.size
            val secondClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == if(isWeekDay) TimeSet.WeekDay.second_start else TimeSet.WeekEnd.second_start }.size
            val thirdClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == if(isWeekDay) TimeSet.WeekDay.third_start else TimeSet.WeekEnd.third_start}.size
            val fourthClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == if(isWeekDay) TimeSet.WeekDay.fourth_start else TimeSet.WeekEnd.fourth_start }.size

            reduce {
                state.copy(
                    loading = false,
                    studyRoomList = studyRoomResult,
                    isWeekDay = isWeekDay,
                    firstClass = firstClass,
                    secondClass = secondClass,
                    thirdClass = thirdClass,
                    fourthClass = fourthClass,
                    totalStudents = totalStudent
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(it))
        }
    }
    fun getSheetByTime(type : Int) = intent{
        reduce {
            state.copy(
                loading = true
            )
        }
        getSheetByTimeUseCase(type).onSuccess { studyRoomResult ->
            reduce {
                state.copy(
                    loading = false,
                    studyRoomList = studyRoomResult,
                    isWeekDay = studyRoomResult.studyRoomList.get(0).timeTable.type == TimeTableType.WEEKDAY,

                    firstClass = null,
                    secondClass = null,
                    thirdClass = null,
                    fourthClass = null
                )
            }
        }.onFailure {exception ->
            reduce {
                state.copy(
                    loading = false,
                    exception = exception
                )
            }
        }
    }

    fun checkStudyRoom(applyId : Int, isChecked : Boolean) = intent{
        reduce {
            state.copy(
                loading = true
            )
        }
        checkStudyRoomUseCase(applyId, isChecked).onSuccess {
            postSideEffect(StudyRoomSideEffect.Toast("자습실 신청 확인에 성공했어요"))
        }.onFailure {exception ->
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }

    fun ctrlStudyRoom(studentId : Int, request : StudyRoomRequest) = intent{
        reduce {
            state.copy(
                loading = true
            )
        }
        ctrlStudyRoomUseCase(studentId, request).onSuccess {
            postSideEffect(StudyRoomSideEffect.Toast("자습실 신청에 성공했어요"))
        }.onFailure {exception ->
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }


}
