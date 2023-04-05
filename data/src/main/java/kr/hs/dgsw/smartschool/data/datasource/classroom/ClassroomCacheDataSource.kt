package kr.hs.dgsw.smartschool.data.datasource.classroom

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

interface ClassroomCacheDataSource {

    suspend fun getAllClassroom(): List<Classroom>

    suspend fun getClassroomById(id: Int): Classroom?

    suspend fun getClassroomByPlaceId(id: Int): Classroom?

    suspend fun deleteAllClassroom()

    suspend fun insertClassroom(classroom: Classroom)

    suspend fun insertClassrooms(classrooms: List<Classroom>)
}
