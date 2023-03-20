package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTableType
import kr.hs.dgsw.smartschool.local.entity.timetable.TimeTableEntity

internal fun List<TimeTableEntity>.toTimeTableList(): List<TimeTable> =
    this.map {
        it.toTimeTable()
    }

internal fun TimeTableEntity.toTimeTable(): TimeTable =
    TimeTable(
        id = id,
        name = name,
        type = type.toTimeTableType(),
        startTime = startTime,
        endTime = endTime,
    )

internal fun String.toTimeTableType(): TimeTableType = when(this) {
    TimeTableType.WEEKDAY.name -> TimeTableType.WEEKDAY
    TimeTableType.WEEKEND.name -> TimeTableType.WEEKEND
    else -> TimeTableType.WEEKDAY
}

internal fun List<TimeTable>.toTimeTableEntityList(): List<TimeTableEntity> =
    this.map {
        it.toTimeTableEntity()
    }

internal fun TimeTable.toTimeTableEntity(): TimeTableEntity =
    TimeTableEntity(
        id = id,
        name = name,
        type = type.name,
        startTime = startTime,
        endTime = endTime,
    )
