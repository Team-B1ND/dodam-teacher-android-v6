package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CheckStudyRoomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CtrlStudyRoomUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetSheetByIdUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetSheetByTimeUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StudyRoomViewModel @Inject constructor(
    private val checkStudyRoomUseCase: CheckStudyRoomUseCase,
    private val ctrlStudyRoomUseCase: CtrlStudyRoomUseCase,
    private val getHistoryByIdUseCase: GetSheetByIdUseCase,
    private val getHistoryByTimeUseCase: GetSheetByTimeUseCase,
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container = container<StudyRoomState, StudyRoomSideEffect>(StudyRoomState())

    fun get(){

    }
}