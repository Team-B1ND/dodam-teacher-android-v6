package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.night_study.NightStudyIdRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.night_study.NightStudyResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface NightStudyService {
    @GET(DodamUrl.NightStudy.NIGHT_DATE)
    suspend fun getNightStudyByDate(
        @Query("DATE") date: String
    ): Response<List<NightStudyResponse>>

    @GET(DodamUrl.NightStudy.NIGHT_STUDY)
    suspend fun getNightStudy(): Response<List<NightStudyResponse>>

    @PATCH(DodamUrl.NightStudy.NIGHT_ALLOW)
    suspend fun allowNightStudy(
        @Body nightStudyIdRequest: NightStudyIdRequest
    )

    @PATCH(DodamUrl.NightStudy.NIGHT_DENY)
    suspend fun cancelNightStudy(
        @Body nightStudyIdRequest: NightStudyIdRequest
    )
}