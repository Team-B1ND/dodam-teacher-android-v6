package kr.hs.dgsw.smartschool.remote.request.night_study

import com.google.gson.annotations.SerializedName

data class NightStudyIdRequest(
    @field:SerializedName("id") val id: Int,
)
