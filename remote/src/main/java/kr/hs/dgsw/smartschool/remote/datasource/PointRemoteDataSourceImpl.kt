package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.point.PointRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.mapper.toPointReasonList
import kr.hs.dgsw.smartschool.remote.request.point.GivePointRequest
import kr.hs.dgsw.smartschool.remote.service.PointService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class PointRemoteDataSourceImpl @Inject constructor(
    private val pointService: PointService,
) : PointRemoteDataSource {

    override suspend fun getPoint(
        studentId: Int,
        pointType: PointType,
    ): List<Point> = dodamApiCall {
        pointService.getPoint(
            studentId,
            pointType.name,
        ).data.toModel()
    }

    override suspend fun givePoint(
        givenDate: String,
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType,
    ): Unit = dodamApiCall {
        pointService.givePoint(
            GivePointRequest(
                givenDate = givenDate,
                place = place.name,
                reason = reason,
                score = score,
                studentId = studentId,
                type = type.name
            )
        )
    }

    override suspend fun getReason(pointType: PointType): List<PointReason> = dodamApiCall {
        pointService.getReason(
            pointType.name
        ).data.toPointReasonList()
    }
}
