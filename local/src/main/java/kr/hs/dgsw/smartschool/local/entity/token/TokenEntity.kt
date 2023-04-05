package kr.hs.dgsw.smartschool.local.entity.token

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.TOKEN
)
data class TokenEntity(
    @PrimaryKey val idx: Int,
    val token: String,
    val refreshToken: String
) {
    constructor(token: String, refreshToken: String) : this(0, token, refreshToken)
}
