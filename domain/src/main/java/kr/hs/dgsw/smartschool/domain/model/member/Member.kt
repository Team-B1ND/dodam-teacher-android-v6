package kr.hs.dgsw.smartschool.domain.model.member

import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import java.time.LocalDateTime

data class Member(
    val id: String,
    val name: String,
    val email: String,
    val role: MemberRole,
    val status: MemberStatus,
    val profileImage: String?,
    val phone: String,
    val student: Student?,
    val teacher: Teacher?,
    val createdAt: String,
    val modifiedAt: String
)
//{
//    constructor(id: String, name: String, role: MemberRole) : this(
//        email = "",
//        id = id,
//        name = name,
//        profileImage = null,
//        role = role,
//        status = MemberStatus.ACTIVE,
//        phone = "",
//        student = null,
//        teacher = null,
//        createdAt = "",
//        modifiedAt = ""
//    )
//
//    constructor() : this(
//        email = "",
//        id = "",
//        name = "",
//        profileImage = null,
//        role = MemberRole.STUDENT,
//        status = MemberStatus.ACTIVE,
//        phone = "",
//        student = null,
//        teacher = null,
//        createdAt = "",
//        modifiedAt = ""
//    )
//}
