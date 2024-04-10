package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetOutsByDateLocalUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutgoingByDate(param.date.toString())
    }

    suspend fun GetOutSleeping(param: Param) = kotlin.runCatching {
        outRepository.getOutSleepingByDate(param.date.toString())
    }

    data class Param(
        val date: LocalDateTime,
    )
}
