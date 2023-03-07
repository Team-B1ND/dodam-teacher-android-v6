package kr.hs.dgsw.smartschool.domain.usecase.classroom

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository

class GetClassroomsUseCase @Inject constructor(
    private val classroomRepository: ClassroomRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        classroomRepository.getClassrooms()
    }
}