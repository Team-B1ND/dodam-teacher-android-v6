package kr.hs.dgsw.smartschool.remote.response.out

import com.google.gson.annotations.SerializedName

data class OutResponse(
    @field:SerializedName("outgoingList") val outgoingList: List<OutDetailResponse>,
    @field:SerializedName("outsleepingList") val outsleepingList: List<OutDetailResponse>,
)
