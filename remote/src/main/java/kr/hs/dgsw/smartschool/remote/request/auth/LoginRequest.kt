package kr.hs.dgsw.smartschool.remote.request.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("pw") val pw: String,
)
