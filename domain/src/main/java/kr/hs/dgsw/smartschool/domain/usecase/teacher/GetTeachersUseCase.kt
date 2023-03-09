package kr.hs.dgsw.smartschool.domain.usecase.teacher

import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import javax.inject.Inject

class GetTeachersUseCase @Inject constructor(
    private val teachersRepository: TeacherRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        teachersRepository.getTeachers()
    }
}
