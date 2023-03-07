package kr.hs.dgsw.smartschool.remote.response.out

import com.google.gson.annotations.SerializedName

data class OutTeacherResponse(
    @field:SerializedName("idx") val idx: Int,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("accessLevel") val accessLevel: Int,
    @field:SerializedName("allowed") val allowed: Int,
    @field:SerializedName("joinDate") val joinDate: String,
    @field:SerializedName("profileImage") val profileImage: String?,
)
