package kr.hs.dgsw.smartschool.remote.request.studyroom

import com.google.gson.annotations.SerializedName

data class StudyRoomRequest (
    @field:SerializedName("placeId") val placeId : Int,
    @field:SerializedName("timeTableId") val timeTableId : Int
)