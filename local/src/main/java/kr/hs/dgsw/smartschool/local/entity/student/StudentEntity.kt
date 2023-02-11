package kr.hs.dgsw.smartschool.local.entity.student

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.entity.classroom.ClassroomEntity
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.STUDENT,
)
data class StudentEntity(
    @PrimaryKey val id: Int,
    val classroom: ClassroomEntity,
    val member: MemberEntity,
    val number: Int,
    val phone: String,
)
