package kr.hs.dgsw.smartschool.domain.usecase.auth

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        authRepository.login(
            id = param.id,
            pw = param.pw,
            enableAutoLogin = param.enableAutoLogin,
        )
    }

    data class Param(
        val id: String,
        val pw: String,
        val enableAutoLogin: Boolean,
    )
}