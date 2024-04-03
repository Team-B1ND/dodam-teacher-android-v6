package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.member.AllMemberResponse
import kr.hs.dgsw.smartschool.remote.response.student.StudentResponse
import kr.hs.dgsw.smartschool.remote.response.teacher.TeacherResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface MemberService {

    @GET(DodamUrl.Member.GET_MEMBERS)
    suspend fun getMembers(): Response<AllMemberResponse>

    @GET(DodamUrl.Member.GET_MY)
    suspend fun getMyInfo(): Response<TeacherResponse>

    @GET(DodamUrl.Member.STUDENT_SORT)
    suspend fun getSortedStudents(): Response<List<StudentResponse>>

    @GET(DodamUrl.Member.MEMBERS)
    suspend fun getStudents(): Response<List<StudentResponse>>

    @GET(DodamUrl.Member.MEMBERS)
    suspend fun getTeachers(): Response<List<TeacherResponse>>
}
