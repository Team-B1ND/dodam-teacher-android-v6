package kr.hs.dgsw.smartschool.domain.usecase.night_study

import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import javax.inject.Inject

class GetNightStudyByDateUseCase @Inject constructor(
    private val nightStudyRepository: NightStudyRepository
) {
    suspend operator fun invoke(date: String) = kotlin.runCatching {
        nightStudyRepository.getNightStudyByDate(date)
    }
}