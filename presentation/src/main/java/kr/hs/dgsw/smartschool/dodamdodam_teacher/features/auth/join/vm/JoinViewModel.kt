package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi.JoinState
import kr.hs.dgsw.smartschool.domain.usecase.auth.JoinUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase,
) : ContainerHost<JoinState, Unit>, ViewModel() {

    override val container = container<JoinState, Unit>(JoinState())

    fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    ) = intent {
        reduce {
            state.copy(
                loading = true,
            )
        }
        joinUseCase(
            param = JoinUseCase.Param(
                email = email,
                id = id,
                name = name,
                phone = phone,
                position = position,
                pw = pw,
                tel = tel,
            )
        ).onSuccess {
            reduce {
                state.copy(
                    loading = false,
                    isSuccess = true,
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
}
