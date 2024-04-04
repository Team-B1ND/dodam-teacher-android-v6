package kr.hs.dgsw.smartschool.remote.request.point

import com.google.gson.annotations.SerializedName

data class GivePointRequest(
    @field:SerializedName("issueAt") val givenDate: String,
    @field:SerializedName("reasonId") val reasonId: Int,
    @field:SerializedName("studentIds") val studentId: List<Int>
)
