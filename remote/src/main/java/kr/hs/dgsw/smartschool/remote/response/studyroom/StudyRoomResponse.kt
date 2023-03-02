package kr.hs.dgsw.smartschool.remote.response.studyroom

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse

data class StudyRoomResponse(
    val id : Int,
    val date : String,
    val timeTable : TimeTable,
    val place: Place,
    val student : Student,
    val status: String,
    val teacher : Teacher
){
    data class TimeTable(
        val id: Int,
        val name : String,
        val type : String,
        val startTime : String,
        val endTime : String
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
        val id : Int
    )
    data class Teacher(
        val id : Int,
        val member: Member,
        val tel : String,
        val position : String,
        val phone : String
    ){
        data class Member(
            val id : Int,
            val name : String,
            val email : String,
            val role : String,
            val status: String,
            val joinDate : String,
            val profileImage : String
        )
    }
}