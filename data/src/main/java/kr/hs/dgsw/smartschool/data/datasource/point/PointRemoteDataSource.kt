package kr.hs.dgsw.smartschool.data.datasource.point

import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType

interface PointRemoteDataSource {

    suspend fun getPoint(
        studentId: Int,
        pointType: PointPlace,
    ): List<Point>

    suspend fun givePoint(
        id: Int,
        givenDate: String,
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType,
    )

    suspend fun getReason(type: PointPlace): List<PointReason>
}
