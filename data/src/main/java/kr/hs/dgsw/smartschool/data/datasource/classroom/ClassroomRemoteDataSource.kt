package kr.hs.dgsw.smartschool.data.datasource.classroom

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

interface ClassroomRemoteDataSource {

    suspend fun getClassrooms(): List<Classroom>
}
