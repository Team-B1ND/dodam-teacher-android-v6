package kr.hs.dgsw.smartschool.domain.usecase.teacher

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository

class GetMyInfoUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        teacherRepository.getMyInfo()
    }
}