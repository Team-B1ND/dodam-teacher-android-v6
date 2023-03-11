package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.schedule.mvi

import java.time.LocalDate
import kr.hs.dgsw.smartschool.components.component.organization.calendar.schedule.Schedule

data class ScheduleState(
    val schedules: List<Schedule> = emptyList(),
    val currentDate: LocalDate = LocalDate.now(),
    val todaySchedules: List<Schedule> = emptyList(),
)

sealed class ScheduleSideEffect {
    data class ShowException(val exception: Throwable): ScheduleSideEffect()
}
