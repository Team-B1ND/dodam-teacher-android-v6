package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StudyRoomService {
    @GET(DodamUrl.StudyRoom.HISTORY)
    suspend fun getAllHistory() : Response<List<StudyRoomResponse>>

    @GET(DodamUrl.StudyRoom.HISTORY_ID)
    suspend fun getHistoryById(
        @Path("id") id : Int
    ) : Response<List<StudyRoomResponse>>

    @POST(DodamUrl.StudyRoom.CHECK)
    suspend fun postStudyRoomCheck(
        @Query("id") id : Int
    ) : Response<Any>

    @POST(DodamUrl.StudyRoom.UNCHECK)
    suspend fun postStudyRoomUnCheck(
        @Path("id") id : Int
    ) : Response<Any>

    @POST(DodamUrl.StudyRoom.CTRL)
    suspend fun postStudyRoomCtrl(
        @Body studentId : Int,
        @Body studyRoomList : List<StudyRoomRequest>
    ) : Response<Any>

}