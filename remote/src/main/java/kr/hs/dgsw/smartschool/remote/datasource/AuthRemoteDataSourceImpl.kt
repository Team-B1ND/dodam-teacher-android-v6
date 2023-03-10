package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.data.auth.LoginData
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthRemoteDataSource
import kr.hs.dgsw.smartschool.domain.exception.UncertifiedRoleException
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.remote.mapper.toLoginData
import kr.hs.dgsw.smartschool.remote.request.auth.JoinRequest
import kr.hs.dgsw.smartschool.remote.request.auth.LoginRequest
import kr.hs.dgsw.smartschool.remote.service.AuthService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRemoteDataSource {

    override suspend fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    ) = dodamApiCall {
        authService.join(
            JoinRequest(email, id, name, phone, position, pw, tel)
        ).data
    }

    override suspend fun login(
        id: String,
        pw: String
    ): LoginData {
        val result = dodamApiCall {
            authService.login(
                LoginRequest(id, pw)
            ).data.toLoginData()
        }
        if (result.member.role == MemberRole.TEACHER || result.member.role == MemberRole.ADMIN)
            return result
        else
            throw UncertifiedRoleException("접근할 수 없는 권한입니다")
    }
}
