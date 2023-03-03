package kr.hs.dgsw.smartschool.domain.usecase.token

import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        tokenRepository.getToken()
    }
}
