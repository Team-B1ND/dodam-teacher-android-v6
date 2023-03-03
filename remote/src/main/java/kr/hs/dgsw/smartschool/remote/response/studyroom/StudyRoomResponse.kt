package kr.hs.dgsw.smartschool.remote.response.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseStatus
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse

data class StudyRoomResponse(
    @field:SerializedName("id") val id : Int,
    @field:SerializedName("date") val date : String,
    @field:SerializedName("timeTable") val timeTable : TimeTable,
    @field:SerializedName("place") val place: Place,
    @field:SerializedName("student") val student : Student,
    @field:SerializedName("status") val status: StudyRoomResponseStatus,
    @field:SerializedName("teacher") val teacher : Teacher
){
    data class TimeTable(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name : String,
        @field:SerializedName("type") val type : TimeTableType,
        @field:SerializedName("startTime") val startTime : String,
        @field:SerializedName("endTime") val endTime : String
    )
    data class Place(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("type") val type: PlaceType,
    ) {
        data class PlaceType(
            @field:SerializedName("id") val id: Int,
            @field:SerializedName("name") val name: String,
        )
    }
    data class Student(
        @field:SerializedName("id") val id : Int
    )

    data class Teacher(
        @field:SerializedName("id") val id : Int,
        @field:SerializedName("member") val member: Member,
        @field:SerializedName("tel") val tel : String,
        @field:SerializedName("position") val position : String,
        @field:SerializedName("phone") val phone : String
    ){
        data class Member(
            @field:SerializedName("id") val id : Int,
            @field:SerializedName("name") val name : String,
            @field:SerializedName("email") val email : String,
            @field:SerializedName("role") val role : MemberResponseRole,
            @field:SerializedName("status") val status: MemberResponseStatus,
            @field:SerializedName("joinDate") val joinDate : String,
            @field:SerializedName("profileImage") val profileImage : String
        )
    }
}