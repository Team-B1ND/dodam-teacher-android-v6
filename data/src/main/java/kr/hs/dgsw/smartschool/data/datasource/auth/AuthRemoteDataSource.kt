package kr.hs.dgsw.smartschool.data.datasource.auth

import kr.hs.dgsw.smartschool.data.data.auth.LoginData

interface AuthRemoteDataSource {

    suspend fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    )

    suspend fun login(
        id: String,
        pw: String,
    ): LoginData
}