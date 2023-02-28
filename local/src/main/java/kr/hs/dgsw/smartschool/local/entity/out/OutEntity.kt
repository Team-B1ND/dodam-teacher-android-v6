package kr.hs.dgsw.smartschool.local.entity.out

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.OUT,
)
data class OutEntity(
    @PrimaryKey val id: Int,
    val reason: String,
    val status: String,
    val studentId: Int,
    val teacherId: Int?,
    val startOutDate: String,
    val endOutDate: String,
    val arrivedDate: String?,
    val checkedDate: String?,
)
