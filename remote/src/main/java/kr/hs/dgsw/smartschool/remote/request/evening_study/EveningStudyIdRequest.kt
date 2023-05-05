package kr.hs.dgsw.smartschool.remote.request.evening_study

import com.google.gson.annotations.SerializedName

data class EveningStudyIdRequest(
    @field:SerializedName("id") val id: Int,
)
