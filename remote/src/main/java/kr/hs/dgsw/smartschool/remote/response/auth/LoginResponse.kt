package kr.hs.dgsw.smartschool.remote.response.auth

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse

data class LoginResponse(
    @field:SerializedName("member") val member: MemberResponse,
    @field:SerializedName("refreshToken") val refreshToken: String,
    @field:SerializedName("accessToken") val token: String,
)
