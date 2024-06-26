package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.DataSourceRepository
import kr.hs.dgsw.smartschool.data.datasource.point.PointRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    override val datasource: PointRemoteDataSource,
) : DataSourceRepository<PointRemoteDataSource>, PointRepository {

    override suspend fun getPoint(studentId: Int, type: PointPlace): List<Point> {
        return datasource.getPoint(studentId, type)
    }

    override suspend fun givePoint(
        id: Int,
        givenDate: String,
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType,
    ) {
        datasource.givePoint(
            id = id,
            givenDate = givenDate,
            place = place,
            reason = reason,
            score = score,
            studentId = studentId,
            type = type,
        )
    }

    override suspend fun getReason(type: PointPlace): List<PointReason> {
        return datasource.getReason(type)
    }
}
