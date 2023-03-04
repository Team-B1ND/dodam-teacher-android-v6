package kr.hs.dgsw.smartschool.data.datasource.banner

import kr.hs.dgsw.smartschool.domain.model.banner.Banner

interface BannerCacheDataSource {

    suspend fun getActiveBanner(): List<Banner>

    suspend fun insertBanner(banners: List<Banner>)

    suspend fun deleteAllBanner()
}