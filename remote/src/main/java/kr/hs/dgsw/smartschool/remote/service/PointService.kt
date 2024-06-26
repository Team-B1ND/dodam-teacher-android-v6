package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.point.GivePointRequest
import kr.hs.dgsw.smartschool.remote.request.point.MakeReasonPointRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.point.PointReasonResponse
import kr.hs.dgsw.smartschool.remote.response.point.PointResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PointService {

    @GET(DodamUrl.Point.GET_POINT)
    suspend fun getPoint(
        @Path("studentId") studentId: Int,
        @Query("type") type: String
    ): Response<List<PointResponse>>

    @POST(DodamUrl.Point.GIVE_POINT)
    suspend fun givePoint(
        @Body givePointRequest: GivePointRequest
    ): Response<Unit>

    @GET(DodamUrl.Point.REASON)
    suspend fun getReason(
        @Query("type") type: String,
    ): Response<List<PointReasonResponse>>

    @POST(DodamUrl.Point.REASON)
    suspend fun makeReason(
        @Body makeReasonPointRequest: MakeReasonPointRequest
    ): Response<List<PointReasonResponse>>
}
