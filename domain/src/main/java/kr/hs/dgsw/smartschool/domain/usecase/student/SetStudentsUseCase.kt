package kr.hs.dgsw.smartschool.domain.usecase.student

import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import javax.inject.Inject

class SetStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        studentRepository.setStudents()
    }
}
