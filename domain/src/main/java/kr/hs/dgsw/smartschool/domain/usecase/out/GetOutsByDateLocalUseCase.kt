package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetOutsByDateLocalUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutgoingByDateLocal(param.date)
    }

    suspend fun getOutSleeping(param: Param) = kotlin.runCatching {
        outRepository.getOutSleepingByDateLocal(param.date)
    }

    data class Param(
        val date: LocalDate,
    )
}
