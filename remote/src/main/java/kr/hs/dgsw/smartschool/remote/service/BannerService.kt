package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.banner.BannerResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface BannerService {

    @GET(DodamUrl.Banner.ACTIVE_BANNER)
    suspend fun getActiveBanner(): Response<List<BannerResponse>>
}