package kr.hs.dgsw.smartschool.domain.usecase.point

import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class GetPointReasonUseCase @Inject constructor(
    private val pointRepository: PointRepository
) {

    suspend operator fun invoke(pointType: PointPlace) = kotlin.runCatching {
        pointRepository.getReason(pointType)
    }
}
