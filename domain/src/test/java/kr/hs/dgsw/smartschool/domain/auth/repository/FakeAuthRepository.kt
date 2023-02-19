package kr.hs.dgsw.smartschool.domain.auth.repository

import java.time.LocalDateTime
import kr.hs.dgsw.smartschool.domain.exception.BadRequestException
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.MemberStatus
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository

class FakeAuthRepository: AuthRepository {

    private val members = mutableListOf<Member>()
    private val accounts = mutableListOf<FakeAccount>()

    override suspend fun join(
        email: String,
        id: String,
        name: String,
        phone: String,
        position: String,
        pw: String,
        tel: String,
    ) {
        members.add(Member(email, id, LocalDateTime.now(), name, null, MemberRole.TEACHER, MemberStatus.ACTIVE))
        accounts.add(FakeAccount(id, pw))
    }

    override suspend fun login(id: String, pw: String): Token {
        val member = members.find { it.id == id }
        val account = accounts.find { it.id == member?.id }

        if (account?.pw == pw) {
            return Token("Test Token", "Test Token")
        } else {
            throw BadRequestException(message = "로그인 실패", fieldErrors = emptyList())
        }
    }

    override suspend fun logout() {
        accounts.removeFirst()
    }

    private data class FakeAccount(
        val id: String,
        val pw: String,
    )
}