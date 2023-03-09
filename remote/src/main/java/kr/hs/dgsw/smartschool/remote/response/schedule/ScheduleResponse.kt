package kr.hs.dgsw.smartschool.remote.response.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("place") val place: String,
    @field:SerializedName("startDate") val startDate: String,
    @field:SerializedName("endDate") val endDate: String,
    @field:SerializedName("target") val target: String,
)
