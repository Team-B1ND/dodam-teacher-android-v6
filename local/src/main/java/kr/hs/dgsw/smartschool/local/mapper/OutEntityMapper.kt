package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeHourToLocalDate
import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDate
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.local.entity.out.OutEntity

internal fun OutEntity.toModel(): OutItem =
    OutItem(
        id = id,
        reason = reason,
        status = status.toOutStatus(),
        studentId = studentId,
        teacherId = teacherId,
        startOutDate = startOutDate.yearDateTimeHourToLocalDate(),
        endOutDate = endOutDate.yearDateTimeHourToLocalDate(),
        arrivedDate = arrivedDate?.yearDateTimeHourToLocalDate(),
        checkedDate = checkedDate?.yearDateTimeHourToLocalDate(),
    )

internal fun OutItem.toEntity(): OutEntity {
    return OutEntity(
        id = id,
        reason = reason,
        status = status.name,
        studentId = studentId,
        teacherId = teacherId,
        startOutDate = startOutDate.toString(),
        endOutDate = endOutDate.toString(),
        arrivedDate = arrivedDate?.toString(),
        checkedDate = checkedDate?.toString()
    )
}

internal fun String.toOutStatus(): OutStatus =
    when (this) {
        OutStatus.ALLOWED.name -> OutStatus.ALLOWED
        OutStatus.PENDING.name -> OutStatus.PENDING
        OutStatus.DENIED.name -> OutStatus.DENIED
        else -> OutStatus.PENDING
    }
