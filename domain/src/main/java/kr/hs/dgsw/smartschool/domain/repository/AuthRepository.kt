package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.token.Token

interface AuthRepository {

    suspend fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    )

    suspend fun login(id: String, pw: String, enableAutoLogin: Boolean): Token

    suspend fun getIsAutoLogin(): Boolean

    suspend fun logout()
}