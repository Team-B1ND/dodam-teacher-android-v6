package kr.hs.dgsw.smartschool.remote.response.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseStatus
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse
import kr.hs.dgsw.smartschool.remote.response.timetable.TimeTableResponse

data class StudyRoomResponse(
    @field:SerializedName("id") val id : Int,
    @field:SerializedName("date") val date : String,
    @field:SerializedName("timeTable") val timeTable : TimeTableResponse,
    @field:SerializedName("place") val place: PlaceResponse,
    @field:SerializedName("student") val student : Student,
    @field:SerializedName("status") val status: StudyRoomResponseStatus,
    @field:SerializedName("teacher") val teacher : TeacherResponse
){
    data class Student(
        @field:SerializedName("id") val id : Int
    )
}