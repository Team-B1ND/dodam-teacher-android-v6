package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.teacher.TeacherCacheDataSource
import kr.hs.dgsw.smartschool.domain.exception.UnknownException
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    override val remote: MemberRemoteDataSource,
    override val cache: TeacherCacheDataSource,
) : BaseRepository<MemberRemoteDataSource, TeacherCacheDataSource>, TeacherRepository {

    private val NOT_FOUND_TEACHER_MESSAGE = "해당 선생님을 찾을 수 없어요"

    override suspend fun setTeachers(): List<Teacher> =
        remote.getTeachers()

    override suspend fun getTeachers(): List<Teacher> =
        remote.getTeachers()

    override suspend fun getMyInfo(): Teacher =
        remote.getMyInfo()

    override suspend fun getTeacherById(id: Int): Teacher =
        remote.getTeachers().first {
            it.id == id
        }

    override suspend fun getTeacherByMemberId(id: String): Teacher =
        remote.getTeachers().first {
            it.member.id == id
        }
}
