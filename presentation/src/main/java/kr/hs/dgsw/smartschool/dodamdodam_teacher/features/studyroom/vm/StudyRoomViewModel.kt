package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.studyroom.mvi.StudyRoomState
import kr.hs.dgsw.smartschool.domain.usecase.banner.GetActiveBannersUseCase
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMealUseCase
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemoteUseCase
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetAllSheetUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StudyRoomViewModel @Inject constructor(
    private val getAllSheetUseCase: GetAllSheetUseCase,
) : ContainerHost<StudyRoomState, StudyRoomSideEffect>, ViewModel() {

    override val container: Container<StudyRoomState, StudyRoomSideEffect> = container(StudyRoomState())

    init {
        getStudyRoomSheet()
    }

    private fun getStudyRoomSheet() = intent {
        reduce {
            state.copy(loading = true)
        }

        getAllSheetUseCase().onSuccess { studyRoomResult ->
            val eighthClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == "16:30" && it.timeTable.endTime == "17:20" }.size
            val ninethClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == "17:30" && it.timeTable.endTime == "18:20" }.size
            val tenthClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == "19:10" && it.timeTable.endTime == "20:00" }.size
            val eleventhClass = studyRoomResult.studyRoomList.filter { it.timeTable.startTime == "20:10" && it.timeTable.endTime == "21:00" }.size

            reduce {
                state.copy(
                    loading = false,
                    studyRoomList = studyRoomResult
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(StudyRoomSideEffect.ToastError(it))
        }
    }
}
