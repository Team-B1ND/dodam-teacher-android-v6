package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class GetSheetByTimeUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
){
    suspend operator fun invoke(type : Int) = kotlin.runCatching {
        var startTime = ""
        var endTime = ""
        when(type){
            1 -> {
                startTime = "16:30"
                endTime = "17:20"
            }
            2 -> {
                startTime = "17:30"
                endTime = "18:20"
            }
            3 -> {
                startTime = "19:10"
                endTime = "20:00"
            }
            4 -> {
                startTime = "20:10"
                endTime = "21:00"
            }
        }
        studyRoomRepository.getSheetByTime(startTime, endTime)
    }
}