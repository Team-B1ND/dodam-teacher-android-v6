package kr.hs.dgsw.smartschool.domain.usecase.studyroom

import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Inject

class CheckStudyRoomUseCase @Inject constructor(
    private val studyRoomRepository: StudyRoomRepository,
){
    suspend operator fun invoke(studyRoomApplyId : Int, isChecked : Boolean) = kotlin.runCatching {
        studyRoomRepository.checkStudyRoom(studyRoomApplyId, isChecked)
    }
}