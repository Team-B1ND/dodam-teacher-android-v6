package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.reject.RejectReasonRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.night_study.NightStudyResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NightStudyService {
//    @GET(DodamUrl.NightStudy.NIGHT_DATE)
//    suspend fun getNightStudyByDate(
//        @Query("date") date: String
//    ): Response<List<NightStudyResponse>>
//
    @GET(DodamUrl.NightStudy.NIGHT_STUDY)
    suspend fun getNightStudy(): Response<List<NightStudyResponse>>

    @PATCH(DodamUrl.NightStudy.NIGHT_ALLOW)
    suspend fun allowNightStudy(
        @Path("id") id: Int
    )

    @PATCH(DodamUrl.NightStudy.NIGHT_CANCEL)
    suspend fun denyNightStudy(
        @Path("id") id: Int,
        @Body reason: RejectReasonRequest
    )

    @GET(DodamUrl.NightStudy.NIGHT_PENDING)
    suspend fun getPendingNightStudy(): Response<List<NightStudyResponse>>
}
