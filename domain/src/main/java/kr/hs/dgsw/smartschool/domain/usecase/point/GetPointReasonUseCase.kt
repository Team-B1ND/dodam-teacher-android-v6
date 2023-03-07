package kr.hs.dgsw.smartschool.domain.usecase.point

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.PointRepository

class GetPointReasonUseCase @Inject constructor(
    private val pointRepository: PointRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        pointRepository.getReason()
    }
}
