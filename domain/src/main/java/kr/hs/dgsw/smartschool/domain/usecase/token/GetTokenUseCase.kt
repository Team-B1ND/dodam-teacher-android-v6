package kr.hs.dgsw.smartschool.domain.usecase.token

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        tokenRepository.getToken()
    }
}
