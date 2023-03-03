package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutsByDateRemote
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOutsByDateRemote: GetOutsByDateRemote,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

    init {
        getOutsByDate(LocalDate.now())
    }

    fun getOutsByDate(date: LocalDate) = intent {
        getOutsByDateRemote(GetOutsByDateRemote.Param(date.toString())).onSuccess { out ->
            val outgoingsCnt = out.outgoings.filter { it.teacherId == null }.size
            val outsleepingsCnt = out.outsleepings.filter { it.teacherId == null }.size

            reduce {
                state.copy(
                    outUpdateDate = LocalDateTime.now(),
                    outgoingCount = outgoingsCnt,
                    outsleepingCount = outsleepingsCnt,
                )
            }
        }.onFailure {
        }
    }
}
