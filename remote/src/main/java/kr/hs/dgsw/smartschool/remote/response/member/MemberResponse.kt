package kr.hs.dgsw.smartschool.remote.response.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse

data class MemberResponse(
    @field:SerializedName("email") val email: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("createdAt") val createdAt: String?,
    @field:SerializedName("modifiedAt") val modifiedAt: String?,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("profileImage") val profileImage: String?,
    @field:SerializedName("role") val role: MemberResponseRole,
    @field:SerializedName("status") val status: MemberResponseStatus,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("student") val student: StudentResponse?,
    @field:SerializedName("teacher") val teacher: TeacherResponse?,
)