package kr.hs.dgsw.smartschool.local.entity.teacher

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.TEACHER,
)
data class TeacherEntity(
    @PrimaryKey val teacherId: Int,
    val memberId: String,
    val phone: String,
    val position: String,
    val tel: String,
)
