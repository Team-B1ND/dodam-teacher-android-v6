package kr.hs.dgsw.smartschool.domain.usecase.point

import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class GivePointUseCase @Inject constructor(
    private val pointRepository: PointRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        pointRepository.givePoint(
            givenDate = param.givenDate,
            place = param.place,
            reason = param.reason,
            score = param.score,
            studentId = param.studentId,
            type = param.type,
        )
    }

    data class Param(
        val givenDate: String,
        val place: PointPlace,
        val reason: String,
        val score: Int,
        val studentId: List<Int>,
        val type: PointType,
    )
}
