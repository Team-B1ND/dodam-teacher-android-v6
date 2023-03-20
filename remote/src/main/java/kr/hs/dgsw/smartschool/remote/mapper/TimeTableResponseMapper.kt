package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableResponse
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableResponseType

internal fun List<TimeTableResponse>.toModel(): List<TimeTable> =
    this.map {
        it.toModel()
    }

internal fun TimeTableResponse.toModel(): TimeTable =
    TimeTable(
        id = id,
        name = name,
        type = type.toTimeTableType(),
        startTime = startTime,
        endTime = endTime
    )

internal fun TimeTableResponseType.toTimeTableType(): TimeTableType =
    when (this.name) {
        TimeTableResponseType.WEEKDAY.name -> TimeTableType.WEEKDAY
        TimeTableResponseType.WEEKEND.name -> TimeTableType.WEEKEND
        else -> TimeTableType.WEEKDAY
    }
