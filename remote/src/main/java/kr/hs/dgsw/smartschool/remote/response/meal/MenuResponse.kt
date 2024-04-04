package kr.hs.dgsw.smartschool.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @field:SerializedName("details")
    val details: List<MenuDetailResponse>,

    @field:SerializedName("calorie")
    val calorie: Double,
)