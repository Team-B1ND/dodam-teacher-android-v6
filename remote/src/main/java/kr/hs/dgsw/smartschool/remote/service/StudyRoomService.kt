package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.studyroom.StudyRoomResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface StudyRoomService {
    @GET(DodamUrl.StudyRoom.HISTORY)
    suspend fun getAllHistory() : Response<StudyRoomResponse>
}