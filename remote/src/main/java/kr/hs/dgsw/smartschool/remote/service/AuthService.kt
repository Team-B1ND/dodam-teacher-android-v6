package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.remote.request.auth.LoginRequest
import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.auth.LoginResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(DodamUrl.Member.JOIN)
    suspend fun join(
        @Body joinRequest: JoinRequest,
    ): Response<Unit>

    @POST(DodamUrl.Auth.LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>
}
