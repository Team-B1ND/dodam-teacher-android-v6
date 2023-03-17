package kr.hs.dgsw.smartschool.local.entity.calorie

import androidx.room.Entity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.CALORIE,
    primaryKeys = ["year", "month", "day"]
)
data class CalorieEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val exists: Boolean,
    val breakfast: Double,
    val lunch: Double,
    val dinner: Double,
)
