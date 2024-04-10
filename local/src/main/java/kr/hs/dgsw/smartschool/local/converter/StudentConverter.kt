package kr.hs.dgsw.smartschool.local.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.smartschool.domain.model.member.student.Student

class StudentConverter {
    @TypeConverter
    fun fromStudent(student: Student?): String? {
        return student?.toJsonString() // Student 클래스의 toJsonString() 메서드를 사용하여 문자열로 변환
    }

    @TypeConverter
    fun toStudent(studentString: String?): Student? {
        return studentString?.let { Student.fromJsonString(it) } // Student 클래스의 fromJsonString() 메서드를 사용하여 문자열을 객체로 변환
    }
}
