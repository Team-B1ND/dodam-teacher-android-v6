package kr.hs.dgsw.smartschool.remote.request.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest

data class StudyRoomCtrlRequest (
    @field:SerializedName("studentId") val studentId : Int,
    @field:SerializedName("studyRoomList") val studyRoomList : List<RequestItem>

)
data class RequestItem (
    @field:SerializedName("placeId")
    val placeId: Int,
    @field:SerializedName("timeTableId")
    val timeTableId: Int
)
