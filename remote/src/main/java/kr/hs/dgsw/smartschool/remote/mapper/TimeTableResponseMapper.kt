package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableResponse
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableType

internal fun TimeTableResponse.toModel(): TimeTable =
    TimeTable(
        id = id,
        name = name,
        type = type.toTimeTableType(),
        startTime = startTime,
        endTime = endTime
    )

internal fun List<TimeTableResponse>.toModel(): List<TimeTable> =
    this.map {
        it.toModel()
    }

internal fun TimeTableType.toTimeTableType(): kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType =
    when (this.name) {
        TimeTableType.WEEKDAY.name -> kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType.WEEKDAY
        TimeTableType.WEEKEND.name -> kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType.WEEKEND
        else -> kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType.WEEKDAY
    }
