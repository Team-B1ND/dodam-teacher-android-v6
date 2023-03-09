package kr.hs.dgsw.smartschool.domain.usecase.classroom

import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository
import javax.inject.Inject

class GetClassroomByIdUseCase @Inject constructor(
    private val classroomRepository: ClassroomRepository
) {

    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        classroomRepository.getClassroomById(id)
    }
}
