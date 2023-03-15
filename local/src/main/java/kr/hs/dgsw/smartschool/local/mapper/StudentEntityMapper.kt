package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity

internal fun List<StudentEntity>.toModel(): List<Student> =
    this.map {
        it.toModel()
    }

internal fun StudentEntity.toModel(): Student =
    Student(
        classroom = Classroom(id = classroomId),
        id = studentId,
        member = Member(id = memberId, name = memberName, role = MemberRole.STUDENT,),
        number = number,
        phone = phone,
    )

internal fun List<Student>.toEntity(): List<StudentEntity> =
    this.map {
        it.toEntity()
    }

internal fun Student.toEntity(): StudentEntity =
    StudentEntity(
        studentId = id,
        memberId = member.id,
        memberName = member.name,
        classroomId = classroom.id,
        number = number,
        phone = phone,
    )
