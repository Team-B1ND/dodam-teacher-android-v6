package kr.hs.dgsw.smartschool.local.entity.member

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.domain.model.member.MemberRole
import kr.hs.dgsw.smartschool.domain.model.member.MemberStatus
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.member.teacher.Teacher
import kr.hs.dgsw.smartschool.local.table.DodamTable
import java.time.LocalDateTime

@Entity(
    tableName = DodamTable.MEMBER,
)
data class MemberEntity(
    @PrimaryKey val id: String,
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
