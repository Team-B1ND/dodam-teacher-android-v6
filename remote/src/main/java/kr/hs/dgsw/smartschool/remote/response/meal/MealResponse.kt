package kr.hs.dgsw.smartschool.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @field:SerializedName("breakfast")
    val breakfast: String?,

    @field:SerializedName("lunch")
    val lunch: String?,

    @field:SerializedName("dinner")
    val dinner: String?,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("exists")
    val exists: Boolean,
)
