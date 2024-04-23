package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDate
import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDateT
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.model.out.OutType
import kr.hs.dgsw.smartschool.remote.response.out.OutDetailResponse
import kr.hs.dgsw.smartschool.remote.response.out.OutResponse
import kr.hs.dgsw.smartschool.remote.response.out.OutResponseStatus

internal fun List<OutResponse>.toModel(): List<Out> =
    this.map {
        it.toOut()
    }
internal fun OutResponse.toOut(): Out {
    return Out(
        id = id,
        reason = reason,
        status = status.toOutStatus(),
        student = student.toModel(),
        rejectReason = rejectReason ?: "",
        startOutDate = startOutDate.split("T")[0],
        endOutDate = endOutDate.split("T")[0],
        createdAt = createdAt.yearDateTimeToLocalDateT().toString(),

        modifiedAt = modifiedAt.yearDateTimeToLocalDateT().toString()
    )
}

internal fun OutResponseStatus.toOutStatus(): OutStatus =
    when (this.name) {
        OutStatus.ALLOWED.name -> OutStatus.ALLOWED
        OutStatus.PENDING.name -> OutStatus.PENDING
        OutStatus.REJECTED.name -> OutStatus.REJECTED
        else -> OutStatus.PENDING
    }
