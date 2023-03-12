package kr.hs.dgsw.smartschool.domain.usecase.itmap

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository

class GetCompaniesUseCase @Inject constructor(
    private val itmapRepository: ItmapRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        itmapRepository.getCompanies()
    }
}
