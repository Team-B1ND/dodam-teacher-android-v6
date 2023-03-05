package kr.hs.dgsw.smartschool.domain.model.studyroom

import com.google.gson.annotations.SerializedName

data class StudyRoomRequest(
    @field:SerializedName("studyRoomList") val studyRoomList : List<RequestStudyRoom>
){
    data class RequestStudyRoom(
        @field:SerializedName("placeId") val placeId : Int,
        @field:SerializedName("timeTableId") val timeTableId : Int
    )
}
