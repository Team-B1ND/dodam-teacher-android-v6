package kr.hs.dgsw.smartschool.local.entity.student

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.STUDENT,
)
data class StudentEntity(
    @PrimaryKey val studentId: Int,
    val memberId: String,
    val memberName: String,
    val classroomId: Int,
    val number: Int,
    val phone: String,
)
