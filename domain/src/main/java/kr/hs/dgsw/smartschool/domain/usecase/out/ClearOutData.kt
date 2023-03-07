package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class ClearOutData @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        outRepository.clearOutData()
    }
}
