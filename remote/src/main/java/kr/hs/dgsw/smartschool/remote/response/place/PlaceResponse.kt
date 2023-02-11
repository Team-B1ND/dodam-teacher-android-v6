package kr.hs.dgsw.smartschool.remote.response.place

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("type") val type: PlaceType,
) {
    data class PlaceType(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
    )
}