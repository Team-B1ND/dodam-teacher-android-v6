package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class AllowOutgoingUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.allowOutgoing(param.ids)
    }

    data class Param(
        val ids: List<Int>,
    )
}
