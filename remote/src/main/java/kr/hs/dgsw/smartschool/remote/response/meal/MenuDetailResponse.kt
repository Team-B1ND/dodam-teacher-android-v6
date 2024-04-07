package kr.hs.dgsw.smartschool.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MenuDetailResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("allergies")
    val allergies: List<Int>,
)
