package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.evening_study.EveningStudyIdRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.evening_study.EveningStudyResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface EveningStudyService {
    @GET(DodamUrl.EveningStudy.EVENING_DATE)
    suspend fun getEveningStudyByDate(
        @Query("DATE") date: String
    ): Response<List<EveningStudyResponse>>

    @GET(DodamUrl.EveningStudy.EVENING_STUDY)
    suspend fun getEveningStudy(): Response<List<EveningStudyResponse>>

    @PATCH(DodamUrl.EveningStudy.EVENING_ALLOW)
    suspend fun allowEveningStudy(
        @Body eveningStudyIdRequest: EveningStudyIdRequest
    )

    @PATCH(DodamUrl.EveningStudy.EVENING_DENY)
    suspend fun cancelEveningStudy(
        @Body eveningStudyIdRequest: EveningStudyIdRequest
    )
}