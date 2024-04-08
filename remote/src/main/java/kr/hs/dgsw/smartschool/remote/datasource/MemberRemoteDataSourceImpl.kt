package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.remote.mapper.toMember
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.mapper.toModelStudent
import kr.hs.dgsw.smartschool.remote.response.member.MemberResponseRole
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

    override suspend fun getMyInfo(): Teacher = dodamApiCall {
        memberService.getMyInfo().data.toModel()
    }

    override suspend fun getSortedStudents(): List<Student> = dodamApiCall {
        memberService.getSortedStudents().data
            .filter {
                it.role == MemberResponseRole.STUDENT
            }.map {
                it.toModelStudent()
            }
    }

    override suspend fun getStudents(): List<Student> = dodamApiCall {
        memberService.getStudents().data
            .filter {
                it.role == MemberResponseRole.STUDENT
            }.map {
                it.toModelStudent()
            }
    }

    override suspend fun getTeachers(): List<Teacher> = dodamApiCall {
        memberService.getTeachers().data
            .filter {
                it.role == MemberResponseRole.TEACHER
            }.map {
                it.toModel()
            }
    }
}
