package kr.hs.dgsw.smartschool.remote.response.teacher

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseStatus

data class TeacherResponse(
    @field:SerializedName("id") val id : Int,
    @field:SerializedName("member") val member: Member,
    @field:SerializedName("tel") val tel : String,
    @field:SerializedName("position") val position : String,
    @field:SerializedName("phone") val phone : String
){
    data class Member(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("email") val email: String,
        @field:SerializedName("role") val role: MemberResponseRole,
        @field:SerializedName("status") val status: MemberResponseStatus,
        @field:SerializedName("joinDate") val joinDate: String,
        @field:SerializedName("profileImage") val profileImage: String
    )
}
