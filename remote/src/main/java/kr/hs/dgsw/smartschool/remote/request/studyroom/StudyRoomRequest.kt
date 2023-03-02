package kr.hs.dgsw.smartschool.remote.request.studyroom

data class StudyRoomRequest (
    val placeId : Int,
    val timeTableId : Int
)