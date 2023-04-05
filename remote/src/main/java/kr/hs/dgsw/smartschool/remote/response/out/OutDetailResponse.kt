package kr.hs.dgsw.smartschool.remote.response.out

import com.google.gson.annotations.SerializedName

data class OutDetailResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("status") val status: OutResponseStatus,
    @field:SerializedName("student") val student: OutStudentResponse,
    @field:SerializedName("teacher") val teacher: OutTeacherResponse?,
    @field:SerializedName("startOutDate") val startOutDate: String,
    @field:SerializedName("endOutDate") val endOutDate: String,
    @field:SerializedName("arrivedDate") val arrivedDate: String?,
    @field:SerializedName("checkedDate") val checkedDate: String?,
)
