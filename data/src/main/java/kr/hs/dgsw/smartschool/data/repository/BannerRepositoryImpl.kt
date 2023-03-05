package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    override val remote: BannerRemoteDataSource,
    override val cache: BannerCacheDataSource
) : BaseRepository<BannerRemoteDataSource, BannerCacheDataSource>, BannerRepository {

    override suspend fun getActiveBanners(enableRemote: Boolean): List<Banner> =
        if (enableRemote)
            remote.getActiveBanner().apply {
                cache.deleteAllBanner()
                cache.insertBanner(this)
            }
        else
            cache.getActiveBanner().ifEmpty {
                remote.getActiveBanner()
            }
}
