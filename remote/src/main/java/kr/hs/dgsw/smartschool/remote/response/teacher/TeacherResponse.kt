package kr.hs.dgsw.smartschool.remote.response.teacher

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse

data class TeacherResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("member") val member: MemberResponse,
    @field:SerializedName("tel") val tel: String,
    @field:SerializedName("position") val position: String,
    @field:SerializedName("phone") val phone: String,
)
