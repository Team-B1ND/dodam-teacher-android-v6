package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface MemberDao : BaseDao<MemberEntity> {

    @Query("SELECT * FROM ${DodamTable.MEMBER}")
    suspend fun getAllMember(): List<MemberEntity>

    @Query("SELECT * FROM ${DodamTable.MEMBER} where id=:id")
    suspend fun getMemberById(id: String): MemberEntity?



    @Query("DELETE FROM ${DodamTable.MEMBER}")
    suspend fun deleteAllMember()
}
