package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

interface ClassroomRepository {

    suspend fun setClassrooms(): List<Classroom>

    suspend fun getClassrooms(): List<Classroom>

    suspend fun getClassroomById(id: Int): Classroom

    suspend fun getClassroomByStudentId(id: Int): Classroom
}
