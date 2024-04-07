package kr.hs.dgsw.smartschool.local.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

class TeacherConverter {
    @TypeConverter
    fun fromTeacher(teacher: Teacher?): String? {
        return teacher?.toJsonString() // Teacher 클래스의 toJsonString() 메서드를 사용하여 문자열로 변환
    }

    @TypeConverter
    fun toTeacher(teacherString: String?): Teacher? {
        return teacherString?.let { Teacher.fromJsonString(it) } // Teacher 클래스의 fromJsonString() 메서드를 사용하여 문자열을 객체로 변환
    }
}
