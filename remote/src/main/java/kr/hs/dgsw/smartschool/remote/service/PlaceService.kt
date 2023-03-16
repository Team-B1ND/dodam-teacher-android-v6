package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface PlaceService {

    @GET(DodamUrl.PLACE)
    suspend fun getPlace(): Response<List<PlaceResponse>>
}
