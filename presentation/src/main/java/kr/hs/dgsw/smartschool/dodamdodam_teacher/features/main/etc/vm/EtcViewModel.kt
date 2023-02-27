package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.etc.mvi.EtcState
import kr.hs.dgsw.smartschool.domain.usecase.auth.LogoutUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class EtcViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
): ContainerHost<EtcState, EtcSideEffect>, ViewModel() {

    override val container: Container<EtcState, EtcSideEffect> = container(EtcState())

    fun logout() = intent {
        reduce {
            state.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            logoutUseCase().onSuccess {
                reduce {
                    state.copy(isLoading = false)
                }
                postSideEffect(EtcSideEffect.SuccessLogout)
            }.onFailure {
                reduce {
                    state.copy(isLoading = false)
                }
                postSideEffect(EtcSideEffect.ToastLogoutErrorMessage(it))
            }
        }
    }
}