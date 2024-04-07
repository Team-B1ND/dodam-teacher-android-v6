package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface MemberService {

    @GET(DodamUrl.Member.MEMBERS)
    suspend fun getMembers(): Response<List<MemberResponse>>

    @GET(DodamUrl.Member.GET_MY)
    suspend fun getMyInfo(): Response<MemberResponse>

//    @GET(DodamUrl.Member.MEMBERS)
//    suspend fun getSortedStudents(): Response<List<MemberResponse>>
//
//    @GET(DodamUrl.Member.MEMBERS)
//    suspend fun getStudents(): Response<List<MemberResponse>>
//
//    @GET(DodamUrl.Member.MEMBERS)
//    suspend fun getTeachers(): Response<List<MemberResponse>>
}
