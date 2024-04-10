package kr.hs.dgsw.smartschool.local.entity.banner

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.BANNER
)
data class BannerEntity(
    @PrimaryKey val id: Int,
    val expiryDateTime: String,
    val image: String,
    val url: String,
    val status: String,
    val title: String,
)
