package kr.hs.dgsw.smartschool.remote.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.data.member.MemberData
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.remote.mapper.toMemberData
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.MemberService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall

class MemberRemoteDataSourceImpl @Inject constructor(
    private val memberService: MemberService,
) : MemberRemoteDataSource {

    override suspend fun getMembers(): MemberData = dodamApiCall {
        memberService.getMembers().data.toMemberData()
    }

    override suspend fun getMyInfo(): Teacher = dodamApiCall {
        memberService.getMyInfo().data.toModel()
    }

    override suspend fun getSortedStudents(): List<Student> = dodamApiCall {
        memberService.getSortedStudents().data.toModel()
    }

    override suspend fun getStudents(): List<Student> = dodamApiCall {
        memberService.getStudents().data.toModel()
    }

    override suspend fun getTeachers(): List<Teacher> = dodamApiCall {
        memberService.getTeachers().data.toModel()
    }
}
