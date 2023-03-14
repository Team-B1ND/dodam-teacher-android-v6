package kr.hs.dgsw.smartschool.remote.response.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import java.time.LocalDateTime

data class MemberResponse(
    @field:SerializedName("email") val email: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("joinDate") val joinDate: String?,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("profileImage") val profileImage: String?,
    @field:SerializedName("role") val role: MemberResponseRole,
    @field:SerializedName("status") val status: MemberResponseStatus,
    @field:SerializedName("student") val student: StudentResponse?,
)
