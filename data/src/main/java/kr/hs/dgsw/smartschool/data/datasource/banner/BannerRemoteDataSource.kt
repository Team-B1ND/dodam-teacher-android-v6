package kr.hs.dgsw.smartschool.data.datasource.banner

import kr.hs.dgsw.smartschool.domain.model.banner.Banner

interface BannerRemoteDataSource {

    suspend fun getActiveBanner(): List<Banner>
}
