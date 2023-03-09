package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.member.MemberCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class MemberCacheDataSourceImpl @Inject constructor(
    private val memberDao: MemberDao,
) : MemberCacheDataSource {

    override suspend fun getMembers(): List<Member> =
        memberDao.getAllMember().toModel()

    override suspend fun getMemberById(id: String): Member? =
        memberDao.getMemberById(id)?.toModel()

    override suspend fun getMemberByTeacherId(id: Int): Member? =
        memberDao.getMemberByTeacherId(id)?.toModel()

    override suspend fun getMemberByStudentId(id: Int): Member? =
        memberDao.getMemberByStudentId(id)?.toModel()

    override suspend fun deleteAllMember() =
        memberDao.deleteAllMember()

    override suspend fun insertMember(member: Member) =
        memberDao.insert(member.toEntity())

    override suspend fun insertMembers(members: List<Member>) =
        memberDao.insert(members.toEntity())
}
