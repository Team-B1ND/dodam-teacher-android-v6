package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kr.hs.dgsw.smartschool.components.component.organization.calendar.DodamBasicCalendarCategory
import kr.hs.dgsw.smartschool.components.component.organization.calendar.schedule.Schedule
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.mvi.ScheduleSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.mvi.ScheduleState
import kr.hs.dgsw.smartschool.domain.usecase.schedule.GetSchedulesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getSchedulesUseCase: GetSchedulesUseCase
) : ContainerHost<ScheduleState, ScheduleSideEffect>, ViewModel() {

    override val container: Container<ScheduleState, ScheduleSideEffect> = container(ScheduleState())

    init {
        getSchedules(LocalDate.now())
    }

    fun getSchedules(currentDate: LocalDate) = intent {
        val startDate = "${currentDate.year}-%02d-01".format(currentDate.monthValue)
        val endDate = "${currentDate.year}-%02d-%02d".format(currentDate.monthValue, currentDate.lengthOfMonth())
        Log.d("ScheduleLog", "$startDate ~ $endDate")
        getSchedulesUseCase(
            GetSchedulesUseCase.Param(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
            )
        ).onSuccess {
            Log.d("ScheduleLog", "Success : ${it.size}")
            reduce {
                state.copy(
                    schedules = it.map { schedule ->
                        Schedule(
                            title = schedule.name,
                            category = when(schedule.target) {
                                "전교생" -> DodamBasicCalendarCategory.AllGrade()
                                "1학년" -> DodamBasicCalendarCategory.FirstGrade()
                                "2학년" -> DodamBasicCalendarCategory.SecondGrade()
                                "3학년" -> DodamBasicCalendarCategory.ThirdGrade()
                                else -> DodamBasicCalendarCategory.Others()
                            },
                            startDateTime = LocalDateTime.parse("${schedule.startDate}T00:00:00"),
                            endDateTime = LocalDateTime.parse("${schedule.endDate}T00:00:00"),
                            location = schedule.place,
                        )
                    }
                )
            }
        }.onFailure {
            postSideEffect(ScheduleSideEffect.ShowException(it))
        }
    }

    fun updateDate(date: LocalDate) = intent {
        reduce {
            state.copy(
                currentDate = date
            )
        }
    }

    fun updateTodaySchedules(schedules: List<Schedule>) = intent {
        reduce {
            state.copy(
                todaySchedules = schedules
            )
        }
    }
}
