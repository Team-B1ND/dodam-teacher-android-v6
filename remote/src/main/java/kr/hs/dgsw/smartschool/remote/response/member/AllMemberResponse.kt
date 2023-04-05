package kr.hs.dgsw.smartschool.remote.response.member

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse

data class AllMemberResponse(
    @field:SerializedName("students") val students: List<StudentResponse>,
    @field:SerializedName("teachers") val teachers: List<TeacherResponse>,
)
