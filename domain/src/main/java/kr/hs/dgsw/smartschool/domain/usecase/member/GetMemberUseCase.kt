package kr.hs.dgsw.smartschool.domain.usecase.member

import kr.hs.dgsw.smartschool.domain.repository.MemberRepository
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        memberRepository.getMemberInfo()
    }

}