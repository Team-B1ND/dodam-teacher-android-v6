package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.classroom.ClassroomResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET

interface ClassroomService {

    @GET(DodamUrl.Classroom.GET_CLASSROOMS)
    suspend fun getClassrooms(): Response<List<ClassroomResponse>>
}