package kr.hs.dgsw.smartschool.local.entity.place

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.PLACE
)
data class PlaceEntity(
    @PrimaryKey val id: Int,
    val name: Int,
    val placeTypeId: Int,
    val placeTypeName: String,
)
