package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomItem
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class CtrlStudyRoomUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        studyRoomRepository.ctrlStudyRoom(param.studentId, param.studyRoomList)
    }

    data class Param(
        val studentId: Int,
        val studyRoomList: List<StudyRoomItem>,
    )
}
