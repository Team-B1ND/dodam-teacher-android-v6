package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.itmap.CompanyEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface ItmapDao : BaseDao<CompanyEntity> {

    @Query("SELECT * FROM ${DodamTable.ITMAP}")
    suspend fun getCompanies(): List<CompanyEntity>

    @Query("DELETE FROM ${DodamTable.ITMAP}")
    suspend fun deleteCompanies()
}