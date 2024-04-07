package kr.hs.dgsw.smartschool.domain.usecase.point

import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class GetPointUseCase @Inject constructor(
    private val pointRepository: PointRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        pointRepository.getPoint(
            studentId = param.studentId,
            type = param.type,
        )
    }

    data class Param(
        val studentId: Int,
        val type: PointPlace,
    )
}
