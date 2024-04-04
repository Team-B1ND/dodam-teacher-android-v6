package kr.hs.dgsw.smartschool.remote.response.student

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.classroom.ClassroomResponse
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse

data class StudentResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("grade") val grade: Int,
    @field:SerializedName("room") val room: Int,
    @field:SerializedName("number") val number: Int,

)
