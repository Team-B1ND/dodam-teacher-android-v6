package kr.hs.dgsw.smartschool.local.entity.timetable

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(tableName = DodamTable.TIMETABLE)
data class TimeTableEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val startTime: String,
    val endTime: String,
)
