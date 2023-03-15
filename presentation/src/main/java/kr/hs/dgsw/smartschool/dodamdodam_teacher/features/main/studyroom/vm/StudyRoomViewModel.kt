package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeSet
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTable
import kr.hs.dgsw.smartschool.domain.model.studyroom.timetable.TimeTableType
import kr.hs.dgsw.smartschool.domain.usecase.classroom.GetClassroomByIdUseCase
import kr.hs.dgsw.smartschool.domain.usecase.place.GetPlacesUseCase
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
    private val getSheetByUserIdUseCase: GetSheetByUserIdUseCase,
    private val checkStudyRoomUseCase: CheckStudyRoomUseCase,
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getClassroomByIdUseCase : GetClassroomByIdUseCase
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container: Container<StudyRoomState, StudyRoomSideEffect> = container(
        StudyRoomState()
    )

    private var totalStudent = 0

    init {
        getStudents()
        getStudyRoomSheet()
        getClassrooms()
        getPlaces()
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
            val isWeekDay: Boolean =
                studyRoomResult.studyRoomList!!.get(0).timeTable.type == TimeTableType.WEEKDAY

            val firstClass =
                studyRoomResult.studyRoomList!!.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.first_start else TimeSet.WeekEnd.first_start }.size
            val secondClass =
                studyRoomResult.studyRoomList!!.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.second_start else TimeSet.WeekEnd.second_start }.size
            val thirdClass =
                studyRoomResult.studyRoomList!!.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.third_start else TimeSet.WeekEnd.third_start }.size
            val fourthClass =
                studyRoomResult.studyRoomList!!.filter { it.timeTable.startTime == if (isWeekDay) TimeSet.WeekDay.fourth_start else TimeSet.WeekEnd.fourth_start }.size

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

    fun getSheetByTime(type: Int) = intent {
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
                    isWeekDay = studyRoomResult.studyRoomList!!.get(0).timeTable.type == TimeTableType.WEEKDAY,
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
    //자습실 신청 관리할 때, 현재 유저의 정보를 불러오기 위해 사용합니다.
    //student,
    fun getSheetById(student: Student) = intent {
        val timeTableList = mutableListOf<TimeTable>()
        val placeList = mutableListOf<Place>()
        reduce {
            state.copy(
                loading = true
            )
        }
        getSheetByUserIdUseCase(student.id).onSuccess { studyRoomResult ->
            studyRoomResult.studyRoomList!!.forEach {
                when(it.timeTable.startTime) {
                    if (state.isWeekDay!!) TimeSet.WeekEnd.first_start
                    else TimeSet.WeekDay.first_start -> {
                        timeTableList.add(0,it.timeTable)
                        placeList.add(0, it.place)
                    }
                    if (state.isWeekDay!!) TimeSet.WeekEnd.second_start
                    else TimeSet.WeekDay.second_start -> {
                        timeTableList.add(1,it.timeTable)
                        placeList.add(1, it.place)
                    }
                    if (state.isWeekDay!!) TimeSet.WeekEnd.third_start
                    else TimeSet.WeekDay.third_start -> {
                        timeTableList.add(2,it.timeTable)
                        placeList.add(2, it.place)
                    }
                    if (state.isWeekDay!!) TimeSet.WeekEnd.fourth_start
                    else TimeSet.WeekDay.fourth_start -> {
                        timeTableList.add(3,it.timeTable)
                        placeList.add(3, it.place)
                    }
                }

            }
            reduce {
                state.copy(
                    student = student,
                    timeTableList = timeTableList,
                    placeList = placeList
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
    private fun getClassrooms() = intent{
        val otherStudents = state.studyRoomList.otherStudents
        val newList = mutableListOf<Student>()
        reduce {
            state.copy(
                loading = true
            )
        }
        otherStudents!!.forEach {students ->
            getClassroomByIdUseCase(students.classroom.id).onSuccess{
                newList.add(
                    Student(
                        classroom = it,
                        id = students.id,
                        member = students.member,
                        number = students.number,
                        phone = students.phone
                    )
                )
                Log.e("",Student(
                    classroom = it,
                    id = students.id,
                    member = students.member,
                    number = students.number,
                    phone = students.phone
                ).toString())
                reduce {
                    state.copy(
                        loading = false,
                        studyRoomList = StudyRoomList(
                            studyRoomList = state.studyRoomList.studyRoomList,
                            otherStudents = newList
                        )
                    )
                }
            }.onFailure { exception ->
                reduce {
                    state.copy(
                        loading = false,
                        studyRoomList =  StudyRoomList(
                            state.studyRoomList.studyRoomList,
                            newList
                        )
                    )
                }
                postSideEffect(StudyRoomSideEffect.ToastError(exception))
            }
        }
    }

    fun getPlaces() = intent {
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
