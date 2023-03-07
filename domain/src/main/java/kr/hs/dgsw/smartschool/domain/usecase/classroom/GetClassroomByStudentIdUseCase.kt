package kr.hs.dgsw.smartschool.domain.usecase.classroom

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository

class GetClassroomByStudentIdUseCase @Inject constructor(
    private val classroomRepository: ClassroomRepository
) {

    suspend operator fun invoke(studentId: Int) = kotlin.runCatching {
        classroomRepository.getClassroomById(studentId)
    }
}