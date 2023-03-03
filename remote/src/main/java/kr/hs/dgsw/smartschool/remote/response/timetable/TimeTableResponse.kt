package kr.hs.dgsw.smartschool.remote.response.timetable

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.studyroom.TimeTableType

data class TimeTableResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name : String,
    @field:SerializedName("type") val type : TimeTableType,
    @field:SerializedName("startTime") val startTime : String,
    @field:SerializedName("endTime") val endTime : String
)
