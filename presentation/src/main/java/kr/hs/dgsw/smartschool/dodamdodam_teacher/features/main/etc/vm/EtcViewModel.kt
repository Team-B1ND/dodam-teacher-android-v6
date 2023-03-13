package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcState
import kr.hs.dgsw.smartschool.domain.usecase.auth.LogoutUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.usecase.teacher.GetMyInfoUseCase

@HiltViewModel
class EtcViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
) : ContainerHost<EtcState, EtcSideEffect>, ViewModel() {

    override val container: Container<EtcState, EtcSideEffect> = container(EtcState())

    init {
        getMyInfo()
    }

    fun getMyInfo() = intent {
        reduce {
            state.copy(
                isLoading = true,
            )
        }
        getMyInfoUseCase().onSuccess {
            reduce {
                state.copy(
                    isLoading = false,
                    myInfo = it,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    isLoading = false,
                )
            }
            postSideEffect(EtcSideEffect.ShowException(it))
        }
    }

    fun logout() = intent {
        reduce {
            state.copy(
                isLoading = true,
            )
        }
        logoutUseCase().onSuccess {
            reduce {
                state.copy(isLoading = false)
            }
            postSideEffect(EtcSideEffect.SuccessLogout)
        }.onFailure {
            reduce {
                state.copy(isLoading = false)
            }
            postSideEffect(EtcSideEffect.ShowException(it))
        }
    }

    fun updateShowPrompt(enable: Boolean) = intent {
        reduce {
            state.copy(
                showPrompt = enable
            )
        }
    }
}
