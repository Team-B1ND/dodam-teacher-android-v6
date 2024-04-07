package kr.hs.dgsw.smartschool.remote.response.night_study

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.out.OutResponseStatus

data class NightStudyResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("status") val status: OutResponseStatus,
    @field:SerializedName("doNeedPhone") val doNeedPhone: Boolean,
    @field:SerializedName("reasonForPhone") val reasonForPhone: String?,
    @field:SerializedName("student") val student: EveningStudyStudentResponse,
    @field:SerializedName("place") val place: String,
    @field:SerializedName("startAt") val startAt: String,
    @field:SerializedName("endAt") val endAt: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("modifiedAt") val modifiedAt: String,
)
