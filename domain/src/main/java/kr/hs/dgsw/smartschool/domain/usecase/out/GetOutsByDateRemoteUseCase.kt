package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class GetOutsByDateRemoteUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutgoingByDate(param.date)
    }

    data class Param(
        val date: String,
    )
}
