package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class CtrlStudyRoomUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
) {
    suspend operator fun invoke(studentId: Int, studyRoomList: StudyRoomRequest) = kotlin.runCatching {
        studyRoomRepository.ctrlStudyRoom(studentId, studyRoomList)
    }
}
