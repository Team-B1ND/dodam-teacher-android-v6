package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.local.entity.teacher.TeacherEntity

internal fun List<TeacherEntity>.toModel(): List<Teacher> =
    this.map {
        it.toModel()
    }

internal fun TeacherEntity.toModel(): Teacher =
    Teacher(
        id = teacherId,
        member = Member(id = memberId, role = MemberRole.TEACHER),
        phone = phone,
        position = position,
        tel = tel,
    )

internal fun List<Teacher>.toEntity(): List<TeacherEntity> =
    this.map {
        it.toEntity()
    }

internal fun Teacher.toEntity(): TeacherEntity =
    TeacherEntity(
        teacherId = id,
        memberId = member.id,
        phone = phone,
        position = position,
        tel = tel,
    )
