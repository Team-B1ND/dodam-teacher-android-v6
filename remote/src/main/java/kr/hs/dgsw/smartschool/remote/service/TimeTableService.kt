package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface TimeTableService {
    @GET(DodamUrl.TIME_TABLE)
    suspend fun getTimeTables(): Response<List<TimeTableResponse>>
}
