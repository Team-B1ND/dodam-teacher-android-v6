package kr.hs.dgsw.smartschool.domain.usecase.out

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.OutRepository

class ClearOutData @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        outRepository.clearOutData()
    }
}
