package kr.hs.dgsw.smartschool.remote.request.point

import com.google.gson.annotations.SerializedName

data class MakeReasonPointRequest(
    @field:SerializedName("pointType") val pointType: String,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("score") val score: Int,
    @field:SerializedName("scoreType") val type: String,
)
