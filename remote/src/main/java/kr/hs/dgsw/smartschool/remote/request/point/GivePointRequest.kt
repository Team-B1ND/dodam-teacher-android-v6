package kr.hs.dgsw.smartschool.remote.request.point

import com.google.gson.annotations.SerializedName

data class GivePointRequest(
    @field:SerializedName("givenDate") val givenDate: String,
    @field:SerializedName("place") val place: String,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("score") val score: Int,
    @field:SerializedName("studentId") val studentId: List<Int>,
    @field:SerializedName("type") val type: String,
)
