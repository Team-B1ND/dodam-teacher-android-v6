package kr.hs.dgsw.smartschool.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @field:SerializedName("breakfast")
    val breakfast: MenuResponse?,

    @field:SerializedName("lunch")
    val lunch: MenuResponse?,

    @field:SerializedName("dinner")
    val dinner: MenuResponse?,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("exists")
    val exists: Boolean,
)
