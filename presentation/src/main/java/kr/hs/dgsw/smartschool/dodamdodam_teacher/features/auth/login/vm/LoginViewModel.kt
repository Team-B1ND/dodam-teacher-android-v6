package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi.LoginSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi.LoginState
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) :  ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun login(
        id: String,
        pw: String,
    ) = intent {
        viewModelScope.launch {
            loginUseCase(
                param = LoginUseCase.Param(
                    id = id,
                    pw = pw,
                )
            ).onSuccess {
                postSideEffect(LoginSideEffect.SuccessLogin(it))
            }.onFailure {
                postSideEffect(LoginSideEffect.ErrorLogin(it))
            }
        }
    }

    fun changeChecked(isCheck: Boolean) = intent {
        reduce {
            state.copy(enableAutoLogin = isCheck)
        }
    }

    fun inputId(text: String) = intent {
        reduce { state.copy(id = text) }
    }

    fun inputPw(text: String) = intent {
        reduce { state.copy(pw = text) }
    }
}
