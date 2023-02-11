package kr.hs.dgsw.smartschool.domain.usecase.auth

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository

class JoinUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        authRepository.join(
            email = param.email,
            id = param.id,
            name = param.name,
            phone = param.phone,
            position = param.position,
            pw = param.pw,
            tel = param.tel
        )
    }

    data class Param(
        val email: String,
        val id: String,
        val name: String,
        val phone: String,
        val position: String,
        val pw: String,
        val tel: String,
    )
}