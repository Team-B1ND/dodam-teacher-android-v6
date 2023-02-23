package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi.JoinSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.auth.join.mvi.JoinState
import kr.hs.dgsw.smartschool.domain.usecase.auth.JoinUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase,
) : ContainerHost<JoinState, JoinSideEffect>, ViewModel() {

    override val container = container<JoinState, JoinSideEffect>(JoinState())

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
                )
            }
            postSideEffect(JoinSideEffect.SuccessJoin)
        }.onFailure {
            reduce {
                state.copy(
                    loading = false,
                )
            }
            postSideEffect(JoinSideEffect.FailJoin(it))
        }
    }

    fun inputId(text: String) = intent {
        reduce { state.copy(id = text) }
    }

    fun inputPw(text: String) = intent {
        reduce { state.copy(pw = text) }
    }

    fun inputCheckedPw(text: String) = intent {
        reduce { state.copy(checkedPw = text) }
    }

    fun inputName(text: String) = intent {
        reduce { state.copy(name = text) }
    }

    fun inputPhone(text: String) = intent {
        reduce { state.copy(phone = text) }
    }

    fun inputEmail(text: String) = intent {
        reduce { state.copy(email = text) }
    }

    fun inputTel(text: String) = intent {
        reduce { state.copy(tel = text) }
    }

    fun inputPosition(text: String) = intent {
        reduce { state.copy(position = text) }
    }

    fun checkTerms(value: Boolean) = intent {
        reduce { state.copy(checkTerms = value) }
    }

    fun setCurrentPage(page: Int) = intent {
        reduce { state.copy(currentPage = page) }
    }
}
