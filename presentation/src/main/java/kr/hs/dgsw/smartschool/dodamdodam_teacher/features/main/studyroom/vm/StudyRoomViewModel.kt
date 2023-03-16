package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeSet
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType
import kr.hs.dgsw.smartschool.domain.usecase.place.GetPlacesUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsUseCase
import kr.hs.dgsw.smartschool.domain.usecase.student.GetStudentsWithClassroomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.*
import kr.hs.dgsw.smartschool.domain.usecase.timetable.GetTimeTablesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StudyRoomViewModel @Inject constructor(
    private val getAllStudyRoomsUseCase: GetAllStudyRoomsUseCase,
    private val getSheetByTimeUseCase: GetSheetByTimeUseCase,
    private val getTimeTablesUseCase: GetTimeTablesUseCase,
    private val checkStudyRoomUseCase: CheckStudyRoomUseCase,
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getStudentsWithClassroomUseCase: GetStudentsWithClassroomUseCase
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container: Container<StudyRoomState, StudyRoomSideEffect> = container(
        StudyRoomState()
    )

    init {
        getTimeTable()
        getPlaces()
        getStudents()
        getAllStudyRooms()
    }

    private fun getStudents() = intent {
        reduce{
            state.copy(
                loading = true
            )
        }
        getStudentsUseCase().onSuccess {
            reduce {
                state.copy(
                    loading = false,
                    totalStudentsCount = it.size
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

    fun getAllStudyRooms() = intent {
        reduce {
            state.copy(loading = true)
        }

        getAllStudyRoomsUseCase().onSuccess { studyRoomResult ->
            val isWeekDay: Boolean =
                studyRoomResult.get(0).timeTable.type == TimeTableType.WEEKDAY
            reduce {
                state.copy(
                    loading = false,
                    studyRoomList = studyRoomResult,
                    isWeekDay = isWeekDay,
                    firstClassCount = studyRoomResult.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.first_start else TimeSet.WeekEnd.first_start }.size,
                    secondClassCount = studyRoomResult.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.second_start else TimeSet.WeekEnd.second_start }.size,
                    thirdClassCount = studyRoomResult.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.third_start else TimeSet.WeekEnd.third_start }.size,
                    fourthClassCount = studyRoomResult.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.fourth_start else TimeSet.WeekEnd.fourth_start }.size,
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

    fun getSheetByTime(type: Int) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        getSheetByTimeUseCase(type).onSuccess { studyRoomResult ->
            reduce {
                state.copy(
                    studyRoomList = studyRoomResult,
                )
            }
        }.onFailure { exception ->
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
        reduce {
            state.copy(
                loading = true
            )
        }
        getStudentsWithClassroomUseCase().onSuccess { studentResult ->
            val newList : MutableList<Student> = studentResult.toMutableList()
            state.studyRoomList.forEach {
                newList.remove(it.student)
            }

            reduce {
                state.copy(
                    loading = false,
                    otherStudents = newList,
                )
            }
        }.onFailure { exception ->
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }
    //자습실 신청 관리할 때, 현재 유저의 정보를 불러오기 위해 사용합니다.
    //student,
    private fun getTimeTable() = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        getTimeTablesUseCase().onSuccess { timeTableList ->
            Log.e("getTimeTables",timeTableList.toString())
            reduce {
                state.copy(
                    loading = false,
                    timeTableList = timeTableList,
                )
            }
        }.onFailure { exception ->
            reduce {
                state.copy(
                    loading = false,
                    exception = exception
                )
            }
        }
    }

    fun addStudentOnState(student: Student) = intent {
        reduce {
            state.copy(
                loading = false,
                student = student
            )
        }
    }

    private fun getPlaces() = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        getPlacesUseCase().onSuccess {result ->
            reduce {
                state.copy(
                    loading = false,
                    placeList = result
                )
            }
        }.onFailure { exception ->
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }

    fun checkStudyRoom(applyId: Int, isChecked: Boolean) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        checkStudyRoomUseCase(applyId, isChecked).onSuccess {
            postSideEffect(StudyRoomSideEffect.Toast("자습실 신청 확인에 성공했어요"))
        }.onFailure { exception ->
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }

    fun ctrlStudyRoom(student: Student, request: StudyRoomRequest) = intent {
        reduce {
            state.copy(
                loading = true
            )
        }
        ctrlStudyRoomUseCase(student.id, request).onSuccess {
            reduce {
                state.copy(
                    loading = true,
                )
            }
            postSideEffect(StudyRoomSideEffect.Toast("${student.member.name} 학생의 자습실 신청에 성공했어요"))
        }.onFailure { exception ->
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(exception))
        }
    }
}
