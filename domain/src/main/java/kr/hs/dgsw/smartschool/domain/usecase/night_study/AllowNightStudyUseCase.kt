package kr.hs.dgsw.smartschool.domain.usecase.night_study

import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import javax.inject.Inject

class AllowNightStudyUseCase @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        nightStudyRepository.allowNightStudy(param.id)
    }

    data class Param(
        val id: Int
    )
}
