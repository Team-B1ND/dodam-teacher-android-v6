package kr.hs.dgsw.smartschool.remote.response.night_study

import com.google.gson.annotations.SerializedName

data class NightStudyResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("allowCheck") val allowCheck: AllowCheckResponse,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("student") val student: EveningStudyStudentResponse,
    @field:SerializedName("place") val place: String,
    @field:SerializedName("startAt") val startAt: String,
    @field:SerializedName("endAt") val endAt: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("phone") val phone: Boolean,
)
