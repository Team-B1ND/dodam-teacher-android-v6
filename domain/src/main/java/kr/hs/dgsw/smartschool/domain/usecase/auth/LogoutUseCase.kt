package kr.hs.dgsw.smartschool.domain.usecase.auth

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.logout()
    }
}