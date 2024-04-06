package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.data.datasource.member.MemberCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.domain.exception.UnknownException
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    override val remote: MemberRemoteDataSource,
    override val cache: MemberCacheDataSource
) : BaseRepository<MemberRemoteDataSource, MemberCacheDataSource>, MemberRepository {

    private val NOT_FOUND_MEMBER_MESSAGE = "해당 맴버를 찾을 수 없어요"

    override suspend fun setMembers(): List<Member> =
        remote.getMembers()

    override suspend fun getMembers(): List<Member> =
        remote.getMembers()

    override suspend fun getMemberById(id: String): Member =
        remote.getMembers().first {
            it.id == id
        }

    override suspend fun getMemberInfo(): Member =
        remote.getMyInfo()

//    override suspend fun getMemberByTeacherId(id: Int): Member =
//        cache.getMemberByTeacherId(id) ?: throw UnknownException(NOT_FOUND_MEMBER_MESSAGE)
//
//    override suspend fun getMemberByStudentId(id: Int): Member =
//        cache.getMemberByStudentId(id) ?: throw UnknownException(NOT_FOUND_MEMBER_MESSAGE)

//    private suspend fun insertMember(memberData: MemberData): List<Member> =
//        cache.deleteAllMember().let {
//            cache.insertMembers(memberData.students.map { it.member } + memberData.teachers.map { it.member })
//                .let {
//                    cache.getMembers()
//                }
//        }
}
