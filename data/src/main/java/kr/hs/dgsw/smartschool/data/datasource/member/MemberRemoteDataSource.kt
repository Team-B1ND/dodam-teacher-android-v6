package kr.hs.dgsw.smartschool.data.datasource.member

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher

interface MemberRemoteDataSource {

    suspend fun getMembers(): List<Member>

    suspend fun getMyInfo(): Member

//    suspend fun getSortedStudents(): List<Student>
//
//    suspend fun getStudents(): List<Student>
//
//    suspend fun getTeachers(): List<Teacher>
}
