package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.model.timetable.TimeSet
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class GetSheetByTimeUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
) {
    suspend operator fun invoke(type: Int,isWeekDay : Boolean) = kotlin.runCatching {
        var startTime = ""
        var endTime = ""
        if(isWeekDay) {
            when (type) {
                1 -> {
                    startTime = TimeSet.WeekDay.first_start
                    endTime = TimeSet.WeekDay.first_end
                }
                2 -> {
                    startTime = TimeSet.WeekDay.second_start
                    endTime = TimeSet.WeekDay.second_end
                }
                3 -> {
                    startTime = TimeSet.WeekDay.third_start
                    endTime = TimeSet.WeekDay.third_end
                }
                4 -> {
                    startTime = TimeSet.WeekDay.fourth_start
                    endTime = TimeSet.WeekDay.fourth_end
                }
            }
        }
        else {
            when (type) {
                1 -> {
                    startTime = TimeSet.WeekEnd.first_start
                    endTime = TimeSet.WeekEnd.first_end
                }
                2 -> {
                    startTime = TimeSet.WeekEnd.second_start
                    endTime = TimeSet.WeekEnd.second_end
                }
                3 -> {
                    startTime = TimeSet.WeekEnd.third_start
                    endTime = TimeSet.WeekEnd.third_end
                }
                4 -> {
                    startTime =TimeSet.WeekEnd.fourth_start
                    endTime = TimeSet.WeekEnd.fourth_end
                }
            }
        }
        studyRoomRepository.getSheetByTime(startTime, endTime)
    }
}
