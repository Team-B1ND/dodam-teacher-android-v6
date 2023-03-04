package kr.hs.dgsw.smartschool.domain.usecase.banner

import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import javax.inject.Inject

class GetActiveBannersUseCase @Inject constructor(
    private val bannerRepository: BannerRepository,
) {

    suspend operator fun invoke(enableRemote: Boolean = false) = kotlin.runCatching {
        bannerRepository.getActiveBanners(enableRemote)
    }
}
