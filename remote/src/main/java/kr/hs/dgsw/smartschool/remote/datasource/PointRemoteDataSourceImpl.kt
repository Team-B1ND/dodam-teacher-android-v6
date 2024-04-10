package kr.hs.dgsw.smartschool.remote.datasource

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
        pointType: PointPlace,
    ): List<Point> = dodamApiCall {
        pointService.getPoint(
            studentId,
            pointType.name,
        ).data.toModel()
    }

    override suspend fun givePoint(
        id: Int,
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
                reasonId = id,
                studentId = studentId,
            )
        )
    }

    override suspend fun getReason(type: PointPlace): List<PointReason> = dodamApiCall {
        val reasons: MutableList<PointReason> = mutableListOf()
        coroutineScope {
            val job1 = async {
                reasons.addAll(
                    pointService.getReason(
                        PointPlace.DORMITORY.name,
                    ).data.toPointReasonList()
                )
            }

            val job2 = async {
                reasons.addAll(
                    pointService.getReason(
                        PointPlace.SCHOOL.name,
                    ).data.toPointReasonList()
                )
            }
            job1.await()
            job2.await()
        }

        return@dodamApiCall reasons
    }
}
