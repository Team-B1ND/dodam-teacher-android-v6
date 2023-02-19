package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.login.mvi.LoginState
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) :  ContainerHost<LoginState, Unit>, ViewModel() {

    override val container = container<LoginState, Unit>(LoginState())

    fun login(
        id: String,
        pw: String,
    ) = intent {

        reduce { state.copy(loading = true) }

        loginUseCase(
            param = LoginUseCase.Param(
                id = id,
                pw = pw,
            )
        ).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                    token = it,
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                    exception = it,
                )
            }
        }
    }

    fun inputId(id: String) = intent {
        reduce { state.copy(id = id) }
    }

    fun inputPw(pw: String) = intent {
        reduce { state.copy(pw = pw) }
    }

    fun changeChecked(isCheck: Boolean) = intent {
        reduce { state.copy(enableAutoLogin = isCheck) }
    }
}
