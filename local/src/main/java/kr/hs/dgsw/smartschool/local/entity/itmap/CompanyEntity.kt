package kr.hs.dgsw.smartschool.local.entity.itmap

import androidx.room.Entity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.ITMAP
)
data class CompanyEntity(
    val id: Int,
    val name: String,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val symbolLogo: String,
    val textLogo: String,
)
