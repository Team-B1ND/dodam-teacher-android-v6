package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.banner.BannerCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.local.dao.BannerDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class BannerCacheDataSourceImpl @Inject constructor(
    private val bannerDao: BannerDao,
) : BannerCacheDataSource {

    override suspend fun getActiveBanner(): List<Banner> =
        bannerDao.getActiveBanners().toModel()

    override suspend fun insertBanner(banners: List<Banner>) =
        bannerDao.insert(banners.toEntity())

    override suspend fun deleteAllBanner() =
        bannerDao.deleteAllBanner()
}
