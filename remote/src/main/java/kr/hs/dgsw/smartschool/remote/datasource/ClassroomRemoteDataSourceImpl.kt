package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.ClassroomService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class ClassroomRemoteDataSourceImpl @Inject constructor(
    private val classroomService: ClassroomService,
) : ClassroomRemoteDataSource {

    override suspend fun getClassrooms(): List<Classroom> = dodamApiCall {
        classroomService.getClassrooms().data.toModel()
    }
}
