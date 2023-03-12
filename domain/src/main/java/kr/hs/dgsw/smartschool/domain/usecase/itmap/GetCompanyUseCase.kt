package kr.hs.dgsw.smartschool.domain.usecase.itmap

import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository
import javax.inject.Inject

class GetCompanyUseCase @Inject constructor(
    private val itmapRepository: ItmapRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        itmapRepository.getCompany(param.id)
    }

    data class Param(
        val id: Int,
    )
}
