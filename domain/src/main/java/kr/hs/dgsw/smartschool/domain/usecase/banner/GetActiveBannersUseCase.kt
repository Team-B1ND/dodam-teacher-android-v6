package kr.hs.dgsw.smartschool.domain.usecase.banner

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository

class GetActiveBannersUseCase @Inject constructor(
    private val bannerRepository: BannerRepository,
) {

    suspend operator fun invoke(enableRemote: Boolean = false) = kotlin.runCatching {
        bannerRepository.getActiveBanners(enableRemote)
    }
}
