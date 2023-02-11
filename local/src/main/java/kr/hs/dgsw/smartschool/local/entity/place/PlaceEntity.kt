package kr.hs.dgsw.smartschool.local.entity.place

import androidx.room.Entity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.PLACE
)
data class PlaceEntity(
    val id: Int,
    val name: Int,
    val type: PlaceType,
) {
    data class PlaceType(
        val id: Int,
        val name: String,
    )
}
