package kr.hs.dgsw.smartschool.local.entity.classroom

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.CLASSROOM
)
data class ClassroomEntity(
    @PrimaryKey val id: Int,
    val placeId: Int,
    val grade: Int,
    val room: Int
)
