package kr.hs.dgsw.smartschool.remote.request.night_study

import com.google.gson.annotations.SerializedName

data class NightStudyIdRequest(
    @field:SerializedName("rejectReason") val rejectReason: String,
)
