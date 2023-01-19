package kr.hs.dgsw.smartschool.local.entity.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(tableName = DodamTable.MEAL)
data class MealEntity(
    @PrimaryKey val id: Int
)
