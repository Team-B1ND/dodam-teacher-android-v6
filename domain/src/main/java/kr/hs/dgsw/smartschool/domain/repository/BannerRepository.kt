package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.banner.Banner

interface BannerRepository {

    suspend fun getActiveBanners(enableRemote: Boolean): List<Banner>
}