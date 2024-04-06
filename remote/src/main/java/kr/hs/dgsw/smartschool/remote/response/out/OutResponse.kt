package kr.hs.dgsw.smartschool.remote.response.out

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

data class OutResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("status") val status: OutResponseStatus,
    @field:SerializedName("student") val student: StudentResponse,
    @field:SerializedName("rejectReason")val rejectReason: String?,
    @field:SerializedName("startOutDate") val startOutDate: String,
    @field:SerializedName("endOutDate") val endOutDate: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("modifiedAt") val modifiedAt: String,
)


