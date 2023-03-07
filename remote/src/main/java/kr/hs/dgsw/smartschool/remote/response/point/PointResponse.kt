package kr.hs.dgsw.smartschool.remote.response.point

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse

data class PointResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("type") val type: PointResponseType,
    @field:SerializedName("place") val place: PointPlaceResponse,
    @field:SerializedName("reason") val reason: String,
    @field:SerializedName("score") val score: Int,
    @field:SerializedName("student") val student: StudentResponse,
    @field:SerializedName("teacher") val teacher: TeacherResponse,
    @field:SerializedName("given_date") val givenDate: String,
)
