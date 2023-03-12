package kr.hs.dgsw.smartschool.local.entity.itmap

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.ITMAP
)
data class CompanyEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val symbolLogo: String,
    val textLogo: String,
)
