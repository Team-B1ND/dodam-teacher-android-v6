package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import kr.hs.dgsw.smartschool.domain.usecase.auth.LogoutUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

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
                postSideEffect(HomeSideEffect.SuccessLogout)
            }.onFailure {
                reduce {
                    state.copy(isLoading = false)
                }
                postSideEffect(HomeSideEffect.ToastLogoutErrorMessage(it))
            }
        }
    }
}
