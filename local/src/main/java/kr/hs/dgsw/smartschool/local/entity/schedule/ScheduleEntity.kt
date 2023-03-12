package kr.hs.dgsw.smartschool.local.entity.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.SCHEDULE
)
data class ScheduleEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val place: String,
    val target: String,
    val startYear: Int,
    val startMonth: Int,
    val startDay: Int,
    val endYear: Int,
    val endMonth: Int,
    val endDay: Int,
)
