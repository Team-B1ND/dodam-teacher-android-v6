package kr.hs.dgsw.smartschool.domain.usecase.schedule

import java.time.LocalDate
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.ScheduleRepository

class GetSchedulesUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        scheduleRepository.getSchedules(param.startDate, param.endDate)
    }

    data class Param(
        val startDate: LocalDate,
        val endDate: LocalDate,
    )
}
