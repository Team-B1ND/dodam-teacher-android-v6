package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.account.AccountEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface AccountDao : BaseDao<AccountEntity> {

    @Query("SELECT * FROM ${DodamTable.ACCOUNT} where idx=0")
    suspend fun getAccount(): AccountEntity?

    @Query("DELETE FROM ${DodamTable.ACCOUNT}")
    suspend fun deleteAccount()
}