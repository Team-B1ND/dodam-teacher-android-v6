package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.mvi.ItmapDetailSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.detail.mvi.ItmapDetailState
import kr.hs.dgsw.smartschool.domain.usecase.itmap.GetCompanyUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ItmapDetailViewModel @Inject constructor(
    private val getCompanyUseCase: GetCompanyUseCase,
) : ContainerHost<ItmapDetailState, ItmapDetailSideEffect>, ViewModel() {

    override val container: Container<ItmapDetailState, ItmapDetailSideEffect> = container(
        ItmapDetailState()
    )

    fun getCompany(id: Int) = intent {
        reduce {
            state.copy(
                loading = true,
            )
        }

        getCompanyUseCase(GetCompanyUseCase.Param(id)).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                    company = it,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(ItmapDetailSideEffect.ShowException(it))
        }
    }
}
