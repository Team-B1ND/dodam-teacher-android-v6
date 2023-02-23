package kr.hs.dgsw.smartschool.local.entity.account

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.ACCOUNT
)
data class AccountEntity(
    @PrimaryKey val idx: Int,
    val id: String,
    val pw: String,
) {
    constructor(id: String, pw: String) : this(0, id, pw)
}
