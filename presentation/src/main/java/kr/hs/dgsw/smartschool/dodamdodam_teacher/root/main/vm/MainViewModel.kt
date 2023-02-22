package kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.mvi.MainSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.root.main.mvi.MainState
import kr.hs.dgsw.smartschool.domain.usecase.auth.GetEnableAutoLoginUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class MainViewModel @Inject constructor(
    private val enableAutoLoginUseCase: GetEnableAutoLoginUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun getEnableAutoLogin() = intent {

        viewModelScope.launch {
            enableAutoLoginUseCase().onSuccess {
                reduce {
                    state.copy(
                        enableAutoLogin = it
                    )
                }
            }.onFailure {
                // TODO message가 null일 경우 아무것도 하지 않기로 수정 필요
                postSideEffect(MainSideEffect.ToastGetEnableAutoLoginErrorMessage(it))
            }
        }
    }

}
