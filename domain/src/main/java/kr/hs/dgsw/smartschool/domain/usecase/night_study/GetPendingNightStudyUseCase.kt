package kr.hs.dgsw.smartschool.domain.usecase.night_study

import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import javax.inject.Inject

class GetPendingNightStudyUseCase @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        nightStudyRepository.getPendingNightStudy()
    }
}