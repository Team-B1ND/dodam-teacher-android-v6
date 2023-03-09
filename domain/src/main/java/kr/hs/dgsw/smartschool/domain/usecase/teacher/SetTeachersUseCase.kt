package kr.hs.dgsw.smartschool.domain.usecase.teacher

import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import javax.inject.Inject

class SetTeachersUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        teacherRepository.setTeachers()
    }
}
