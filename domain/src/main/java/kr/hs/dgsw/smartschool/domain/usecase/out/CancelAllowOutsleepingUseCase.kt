package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class CancelAllowOutsleepingUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.cancelAllowOutsleeping(param.id)
    }

    data class Param(
        val id: Int,
    )
}
