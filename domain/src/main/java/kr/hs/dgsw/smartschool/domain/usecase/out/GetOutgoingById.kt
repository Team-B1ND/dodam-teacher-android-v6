package kr.hs.dgsw.smartschool.domain.usecase.out

import java.time.LocalDateTime
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.OutRepository

class GetOutgoingById @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.getOutgoingById(param.id)
    }

    data class Param(
        val id: Int,
    )
}
