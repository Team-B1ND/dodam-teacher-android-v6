package kr.hs.dgsw.smartschool.remote.request.auth

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @field:SerializedName("email") val email: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("position") val position: String,
    @field:SerializedName("pw") val pw: String,
    @field:SerializedName("tel") val tel: String,
)