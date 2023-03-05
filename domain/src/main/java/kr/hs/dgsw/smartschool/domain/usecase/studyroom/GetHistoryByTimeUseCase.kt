package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class GetHistoryByTimeUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
){
    suspend operator fun invoke(startTime : String, endTime : String) = kotlin.runCatching {
        studyRoomRepository.getHistoryByTime(startTime, endTime)
    }
}