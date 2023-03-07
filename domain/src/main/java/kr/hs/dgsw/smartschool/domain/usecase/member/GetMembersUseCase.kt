package kr.hs.dgsw.smartschool.domain.usecase.member

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.MemberRepository
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository

class GetMembersUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        memberRepository.getMembers()
    }
}
