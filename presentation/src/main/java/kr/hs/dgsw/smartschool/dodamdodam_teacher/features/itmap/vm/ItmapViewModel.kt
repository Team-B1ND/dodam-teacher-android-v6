package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi.ItmapSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi.ItmapState
import kr.hs.dgsw.smartschool.domain.usecase.itmap.GetCompaniesUseCase
import kr.hs.dgsw.smartschool.domain.usecase.itmap.SetCompaniesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ItmapViewModel @Inject constructor(
    private val setCompaniesUseCase: SetCompaniesUseCase,
    private val getCompaniesUseCase: GetCompaniesUseCase,
) : ContainerHost<ItmapState, ItmapSideEffect>, ViewModel() {

    override val container: Container<ItmapState, ItmapSideEffect> = container(ItmapState())

    init {
        getCompanies()
    }

    fun setCompanies() = intent {
        reduce {
            state.copy(
                isRefreshing = true
            )
        }
        setCompaniesUseCase().onSuccess {
            reduce {
                state.copy(
                    companies = it,
                    isRefreshing = false,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isRefreshing = false,
                )
            }
            postSideEffect(ItmapSideEffect.ShowException(it))
        }
    }

    fun getCompanies() = intent {
        reduce {
            state.copy(
                isRefreshing = true
            )
        }
        getCompaniesUseCase().onSuccess {
            reduce {
                state.copy(
                    companies = it,
                    isRefreshing = false,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isRefreshing = false,
                )
            }
            postSideEffect(ItmapSideEffect.ShowException(it))
        }
    }

    fun updateRotated() = intent {
        reduce {
            state.copy(
                isRotated = !state.isRotated
            )
        }
    }
}
