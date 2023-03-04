package kr.hs.dgsw.smartschool.remote.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.BannerService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall

class BannerRemoteDataSourceImpl @Inject constructor(
    private val bannerService: BannerService
) : BannerRemoteDataSource {

    override suspend fun getActiveBanner(): List<Banner> = dodamApiCall {
        bannerService.getActiveBanner().data.toModel()
    }
}
