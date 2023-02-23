package kr.hs.dgsw.smartschool.domain.auth.usecase

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.smartschool.domain.auth.repository.FakeAuthRepository
import kr.hs.dgsw.smartschool.domain.exception.BadRequestException
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.usecase.auth.JoinUseCase
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class AuthUseCasesTest {

    private lateinit var joinUseCase: JoinUseCase
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var fakeRepository: AuthRepository

    @Before
    fun setUp() {
        fakeRepository = FakeAuthRepository()
        joinUseCase = JoinUseCase(fakeRepository)
        loginUseCase = LoginUseCase(fakeRepository)

        val membersToJoin = mutableListOf<JoinUseCase.Param>()
        ('a'..'z').forEachIndexed { index, c ->
            membersToJoin.add(
                JoinUseCase.Param(
                    email = c.toString(),
                    id = c.toString(),
                    name = c.toString(),
                    phone = "",
                    position = "",
                    pw = c.toString(),
                    tel = ""
                )
            )
        }
        membersToJoin.shuffle()
        runBlocking {
            membersToJoin.forEach { fakeRepository.join(it.email, it.id, it.name, it.phone, it.position, it.pw, it.tel) }
        }
    }

    @Test
    fun `Login by account, correct value`(): Unit = runBlocking {
        val result = loginUseCase(LoginUseCase.Param("a", "a", false))

        result.onSuccess {
            assertEquals("Test Token", it.token)
        }.onFailure {
            fail()
        }
    }

    @Test
    fun `Login by account, wrong value`(): Unit = runBlocking {
        val result = loginUseCase(LoginUseCase.Param("a", "b", true))

        result.onFailure {
            assertEquals(BadRequestException::class.java, it::class.java)
        }.onSuccess {
            fail()
        }
    }
}
