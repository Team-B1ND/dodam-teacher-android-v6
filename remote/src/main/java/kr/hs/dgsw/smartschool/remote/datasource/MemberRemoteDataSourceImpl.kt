package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.remote.mapper.toMember
import kr.hs.dgsw.smartschool.remote.service.MemberService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(
    private val memberService: MemberService,
) : MemberRemoteDataSource {

    override suspend fun getMembers(): List<Member> = dodamApiCall {
        memberService.getMembers().data.map {
            it.toMember()
        }
    }

    override suspend fun getMyInfo(): Member = dodamApiCall {
        memberService.getMyInfo().data.toMember()
    }
}
