package kr.hs.dgsw.smartschool.domain.usecase.teacher

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.MemberRepository
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository

class GetTeachersUseCase @Inject constructor(
    private val teachersRepository: TeacherRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        teachersRepository.getTeachers()
    }
}
