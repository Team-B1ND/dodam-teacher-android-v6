package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import kr.hs.dgsw.smartschool.remote.response.schedule.ScheduleResponse

internal fun List<ScheduleResponse>.toModel(): List<Schedule> =
    this.map {
        it.toModel()
    }

internal fun ScheduleResponse.toModel(): Schedule =
    Schedule(
        id = id,
        name = name,
        place = place ?: "장소 없음",
        startDate = date[0].yearDateToLocalDate(),
        endDate = date[date.lastIndex].yearDateToLocalDate(),
        target = targetGrades.joinToString("\n")
    )
