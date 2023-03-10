package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.out.OutIdRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.out.OutDetailResponse
import kr.hs.dgsw.smartschool.remote.response.out.OutResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface OutService {

    @GET(DodamUrl.Out.DATE)
    suspend fun getOutsByDate(
        @Query("date") date: String,
    ): Response<OutResponse>

    @GET(DodamUrl.Out.Outgoing.SINGLE)
    suspend fun getOutgoing(
        @Path("id") id: Int,
    ): Response<OutDetailResponse>

    @DELETE(DodamUrl.Out.Outgoing.DELETE)
    suspend fun deleteOutgoing(
        @Path("outgoingId") id: Int,
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outgoing.ALLOW)
    suspend fun allowOutgoing(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outgoing.CANCEL_ARROW)
    suspend fun cancelAllowOutgoing(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outgoing.DENY)
    suspend fun denyOutgoing(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>

    @GET(DodamUrl.Out.Outsleeping.SINGLE)
    suspend fun getOutsleeping(
        @Path("id") id: Int,
    ): Response<OutDetailResponse>

    @DELETE(DodamUrl.Out.Outsleeping.DELETE)
    suspend fun deleteOutsleeping(
        @Path("outgoingId") id: Int,
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outsleeping.ALLOW)
    suspend fun allowOutsleeping(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outsleeping.CANCEL_ARROW)
    suspend fun cancelAllowOutsleeping(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>

    @PATCH(DodamUrl.Out.Outsleeping.DENY)
    suspend fun denyOutsleeping(
        @Body outIdRequest: OutIdRequest
    ): Response<Unit>
}
