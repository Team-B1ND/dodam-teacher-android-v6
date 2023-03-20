package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class SetStudyRoomsUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        studyRoomRepository.setStudyRoom(
            year = param.year,
            month = param.month,
            day = param.day,
        )
    }

    data class Param(
        val year: Int,
        val month: Int,
        val day: Int,
    )
}
