package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.schedule.ScheduleResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {

    @GET(DodamUrl.Schedule.SEARCH)
    suspend fun getSchedules(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): Response<List<ScheduleResponse>>
}
