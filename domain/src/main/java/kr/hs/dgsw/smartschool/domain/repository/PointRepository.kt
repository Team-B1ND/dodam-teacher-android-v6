package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType

interface PointRepository {

    suspend fun getPoint(studentId: Int, type: PointType): List<Point>

    suspend fun givePoint(
        givenDate: String,
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType,
    )

    suspend fun getReason(pointType: PointType): List<PointReason>
}
