package kr.hs.dgsw.smartschool.remote.response.point

import com.google.gson.annotations.SerializedName

data class PointReasonResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("score") val score: Int,
    @field:SerializedName("scoreType") val type: PointResponseType,
    @field:SerializedName("pointType") val place: PointPlaceResponse,
)
