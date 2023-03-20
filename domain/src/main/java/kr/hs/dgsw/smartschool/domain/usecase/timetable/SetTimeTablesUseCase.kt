package kr.hs.dgsw.smartschool.domain.usecase.timetable

import kr.hs.dgsw.smartschool.domain.repository.TimeTableRepository
import javax.inject.Inject

class SetTimeTablesUseCase @Inject constructor(
    private val timeTableRepository: TimeTableRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        timeTableRepository.setTimeTables()
    }
}
