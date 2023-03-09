package kr.hs.dgsw.smartschool.local.mapper

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import kr.hs.dgsw.smartschool.local.entity.schedule.ScheduleEntity

internal fun List<ScheduleEntity>.toModel(): List<Schedule> =
    this.map {
        it.toModel()
    }

internal fun ScheduleEntity.toModel(): Schedule =
    Schedule(
        id = id,
        name = name,
        place = place,
        startDate = LocalDate.parse("$startYear-%02d-%02d".format(startMonth, startDay)),
        endDate = LocalDate.parse("$endYear-%02d-%02d".format(endMonth, endDay)),
        target = target,
    )

internal fun List<Schedule>.toEntity(): List<ScheduleEntity> =
    this.map {
        it.toEntity()
    }

internal fun Schedule.toEntity(): ScheduleEntity =
    ScheduleEntity(
        id = id,
        name = name,
        place = place,
        target = target,
        startYear = startDate.year,
        startMonth = startDate.monthValue,
        startDay = startDate.dayOfMonth,
        endYear = endDate.year,
        endMonth = endDate.dayOfMonth,
        endDay = endDate.dayOfMonth,
    )
