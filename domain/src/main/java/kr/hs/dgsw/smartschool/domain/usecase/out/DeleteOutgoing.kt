package kr.hs.dgsw.smartschool.domain.usecase.out

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.OutRepository

class DeleteOutgoing @Inject constructor(
    private val outRepository: OutRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        outRepository.deleteOutgoing(param.id)
    }

    data class Param(
        val id: Int,
    )
}
