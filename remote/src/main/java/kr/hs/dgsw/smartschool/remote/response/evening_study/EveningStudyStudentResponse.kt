package kr.hs.dgsw.smartschool.remote.response.evening_study

import com.google.gson.annotations.SerializedName

data class EveningStudyStudentResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("grade") val grade: Int,
    @field:SerializedName("room") val room: Int,
    @field:SerializedName("number") val number: Int,
)
