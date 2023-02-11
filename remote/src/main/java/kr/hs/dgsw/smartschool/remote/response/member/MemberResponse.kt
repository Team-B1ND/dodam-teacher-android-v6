package kr.hs.dgsw.smartschool.remote.response.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

data class MemberResponse(
    @field:SerializedName("email") val email: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("joinDate") val joinDate: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("profileImage") val profileImage: String?,
    @field:SerializedName("role") val role: MemberRole,
    @field:SerializedName("status") val status: MemberStatus,
    @field:SerializedName("student") val student: StudentResponse?,
)
