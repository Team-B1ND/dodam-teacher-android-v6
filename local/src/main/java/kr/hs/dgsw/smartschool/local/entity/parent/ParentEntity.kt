package kr.hs.dgsw.smartschool.local.entity.parent

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.PARENT
)
data class ParentEntity(
    @PrimaryKey val id: Int,
    val studentId: Int,
    val phone: String,
)