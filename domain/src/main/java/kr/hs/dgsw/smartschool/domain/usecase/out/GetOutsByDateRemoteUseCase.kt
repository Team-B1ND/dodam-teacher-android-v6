package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import javax.inject.Inject

class GetOutsByDateRemoteUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutgoingByDateRemote(param.date)
    }

    suspend fun getOutSleeping(param: Param) = kotlin.runCatching {
        outRepository.getOutSleepingByDateRemote(param.date)
    }

    suspend fun getOutSleepingValid() = kotlin.runCatching {
        outRepository.getOutSleepingValid()
    }

    data class Param(
        val date: String,
    )
}
