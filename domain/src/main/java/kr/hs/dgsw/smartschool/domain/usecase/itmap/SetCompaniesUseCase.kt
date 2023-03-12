package kr.hs.dgsw.smartschool.domain.usecase.itmap

import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository
import javax.inject.Inject

class SetCompaniesUseCase @Inject constructor(
    private val itmapRepository: ItmapRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        itmapRepository.setCompanies()
    }
}
