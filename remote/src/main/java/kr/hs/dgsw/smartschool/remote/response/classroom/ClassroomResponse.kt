package kr.hs.dgsw.smartschool.remote.response.classroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse

data class ClassroomResponse(
    @field:SerializedName("grade") val grade: Int,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("place") val place: PlaceResponse,
    @field:SerializedName("room") val room: Int
)
