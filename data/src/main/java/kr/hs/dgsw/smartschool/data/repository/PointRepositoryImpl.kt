package kr.hs.dgsw.smartschool.data.repository

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.point.PointRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointReason
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.domain.repository.PointRepository

class PointRepositoryImpl @Inject constructor(
    override val remote: PointRemoteDataSource,
    override val cache: Any
) : BaseRepository<PointRemoteDataSource, Any>, PointRepository {

    override suspend fun getPoint(studentId: Int, type: PointType): List<Point> {
        return remote.getPoint(studentId, type)
    }

    override suspend fun givePoint(
        givenDate: String,
        place: PointPlace,
        reason: String,
        score: Int,
        studentId: List<Int>,
        type: PointType,
    ) {
        remote.givePoint(
            givenDate = givenDate,
            place = place,
            reason = reason,
            score = score,
            studentId = studentId,
            type = type,
        )
    }

    override suspend fun getReason(): List<PointReason> {
        return remote.getReason()
    }
}