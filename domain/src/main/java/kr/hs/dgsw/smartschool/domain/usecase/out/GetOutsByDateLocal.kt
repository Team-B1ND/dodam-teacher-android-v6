package kr.hs.dgsw.smartschool.domain.usecase.out

import java.time.LocalDateTime
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.OutRepository

class GetOutsByDateLocal @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutsByDateLocal(param.date)
    }

    data class Param(
        val date: LocalDateTime,
    )
}
