package kr.hs.dgsw.smartschool.remote.response.student

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.classroom.ClassroomResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse

data class StudentResponse(
    @field:SerializedName("classroom") val classroom: ClassroomResponse,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("member") val member: MemberResponse,
    @field:SerializedName("number") val number: Int,
    @field:SerializedName("phone") val phone: String,
)
