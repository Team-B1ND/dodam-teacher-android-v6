package kr.hs.dgsw.smartschool.domain.usecase.out

import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetOutsByDateLocalUseCase @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutsByDateLocal(param.date)
    }

    data class Param(
        val date: LocalDateTime,
    )
}
