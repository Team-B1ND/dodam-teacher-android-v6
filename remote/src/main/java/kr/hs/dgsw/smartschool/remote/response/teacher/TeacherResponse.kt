package kr.hs.dgsw.smartschool.remote.response.teacher

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseStatus
import java.time.LocalDateTime

data class TeacherResponse(
    @field:SerializedName("id") val id : Int,
    @field:SerializedName("member") val member: MemberResponse,
    @field:SerializedName("tel") val tel : String,
    @field:SerializedName("position") val position : String,
    @field:SerializedName("phone") val phone : String
){
    data class MemberResponse(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("email") val email: String,
        @field:SerializedName("role") val role: MemberResponseRole,
        @field:SerializedName("status") val status: MemberResponseStatus,
        @field:SerializedName("joinDate") val joinDate: LocalDateTime,
        @field:SerializedName("profileImage") val profileImage: String
    )
}
