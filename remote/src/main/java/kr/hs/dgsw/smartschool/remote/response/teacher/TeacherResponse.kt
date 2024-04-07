package kr.hs.dgsw.smartschool.remote.response.teacher

import com.google.gson.annotations.SerializedName

data class TeacherResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("tel") val tel: String,
    @field:SerializedName("position") val position: String,
)
