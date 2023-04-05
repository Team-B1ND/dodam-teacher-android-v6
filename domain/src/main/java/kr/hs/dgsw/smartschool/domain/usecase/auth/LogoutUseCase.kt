package kr.hs.dgsw.smartschool.domain.usecase.auth

import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.logout()
    }
}
