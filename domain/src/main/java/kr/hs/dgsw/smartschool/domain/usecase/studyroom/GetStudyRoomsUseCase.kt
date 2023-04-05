package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class GetStudyRoomsUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        studyRoomRepository.getStudyRooms()
    }
}
