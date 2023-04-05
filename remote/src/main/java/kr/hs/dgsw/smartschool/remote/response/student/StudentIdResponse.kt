package kr.hs.dgsw.smartschool.remote.response.student

import com.google.gson.annotations.SerializedName

data class StudentIdResponse(
    @SerializedName("id") val studentId: Int,
)
