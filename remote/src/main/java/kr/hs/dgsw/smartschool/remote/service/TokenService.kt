package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST(DodamUrl.Token.TOKEN)
    suspend fun getToken(
        @Body token: String,
    ): Response<String>
}
