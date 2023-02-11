package kr.hs.dgsw.smartschool.local.entity.member

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Entity(
    tableName = DodamTable.MEMBER,
)
data class MemberEntity(
    @PrimaryKey val id: String,
    val email: String,
    val joinDate: String?,
    val name: String,
    val profileImage: String,
    val role: String,
    val status: String,
)
