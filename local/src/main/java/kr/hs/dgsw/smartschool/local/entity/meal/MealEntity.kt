package kr.hs.dgsw.smartschool.local.entity.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.MEAL,
    primaryKeys = ["year", "month", "day"]
)
data class MealEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val exists: Boolean,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
)
