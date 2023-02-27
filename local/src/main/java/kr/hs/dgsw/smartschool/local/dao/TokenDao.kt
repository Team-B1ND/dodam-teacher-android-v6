package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.token.TokenEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface TokenDao : BaseDao<TokenEntity> {

    @Query("SELECT * FROM ${DodamTable.TOKEN} WHERE idx = 0")
    suspend fun getToken(): TokenEntity

    @Query("DELETE FROM ${DodamTable.TOKEN}")
    suspend fun deleteToken()
}
