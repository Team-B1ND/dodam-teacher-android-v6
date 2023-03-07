package kr.hs.dgsw.smartschool.domain.usecase.student

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository

class SetStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        studentRepository.setStudents()
    }
}