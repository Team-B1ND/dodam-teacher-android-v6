package kr.hs.dgsw.smartschool.domain.usecase.classroom

import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository
import javax.inject.Inject

class GetClassroomByStudentIdUseCase @Inject constructor(
    private val classroomRepository: ClassroomRepository
) {

    suspend operator fun invoke(studentId: Int) = kotlin.runCatching {
        classroomRepository.getClassroomById(studentId)
    }
}
