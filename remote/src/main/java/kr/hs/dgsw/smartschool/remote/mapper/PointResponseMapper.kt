package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.remote.response.point.PointPlaceResponse
import kr.hs.dgsw.smartschool.remote.response.point.PointResponse
import kr.hs.dgsw.smartschool.remote.response.point.PointResponseType

internal fun List<PointResponse>.toModel(): List<Point> =
    this.map {
        it.toModel()
    }

internal fun PointResponse.toModel(): Point =
    Point(
        id = id,
        type = type.toPointType(),
        place = place.toPointPlace(),
        reason = reason,
        score = score,
        student = student.toModel(),
        teacher = teacher.toModel(),
        givenDate = givenDate.yearDateToLocalDate(),
    )

internal fun PointResponseType.toPointType(): PointType = when(this.name) {
    PointType.BONUS.name -> PointType.BONUS
    PointType.MINUS.name -> PointType.MINUS
    PointType.OFFSET.name -> PointType.OFFSET
    else -> PointType.OFFSET
}

internal fun PointPlaceResponse.toPointPlace(): PointPlace = when(this.name) {
    PointPlace.DORMITORY.name -> PointPlace.DORMITORY
    PointPlace.SCHOOL.name -> PointPlace.SCHOOL
    else -> PointPlace.DORMITORY
}
