package kr.hs.dgsw.smartschool.remote.response.out

import com.google.gson.annotations.SerializedName

data class OutStudentResponse(
    @field:SerializedName("id") val id: Int,
)
