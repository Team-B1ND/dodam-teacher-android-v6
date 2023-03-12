package kr.hs.dgsw.smartschool.domain.usecase.itmap

import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val itmapRepository: ItmapRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        itmapRepository.getCompanies()
    }
}
