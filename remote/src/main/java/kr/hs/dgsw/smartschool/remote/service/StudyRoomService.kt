package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.remote.request.studyroom.StudyRoomCtrlRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StudyRoomService {
    @GET(DodamUrl.StudyRoom.DATE)
    suspend fun getAllSheet(
        @Query("year") year : Int,
        @Query("month") month : Int,
        @Query("day") day : Int,
    ) : Response<List<StudyRoomResponse>>

    @POST(DodamUrl.StudyRoom.CHECK)
    suspend fun postCheckStudyRoom(
        @Path("id") id : Int
    ) : Response<Unit>

    @POST(DodamUrl.StudyRoom.UNCHECK)
    suspend fun postUnCheckStudyRoom(
        @Path("id") id : Int
    ) : Response<Unit>

    @POST(DodamUrl.StudyRoom.CTRL)
    suspend fun postStudyRoomCtrl(
        @Body studyRoomCtrlRequest: StudyRoomCtrlRequest,
    ) : Response<Unit>

}