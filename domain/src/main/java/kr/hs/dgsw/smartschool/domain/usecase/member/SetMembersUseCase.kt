package kr.hs.dgsw.smartschool.domain.usecase.member

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.MemberRepository

class SetMembersUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        memberRepository.setMembers()
    }
}