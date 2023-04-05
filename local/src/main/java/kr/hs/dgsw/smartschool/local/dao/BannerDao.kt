package kr.hs.dgsw.smartschool.local.dao

import androidx.room.Dao
import androidx.room.Query
import kr.hs.dgsw.smartschool.local.base.BaseDao
import kr.hs.dgsw.smartschool.local.entity.banner.BannerEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Dao
interface BannerDao : BaseDao<BannerEntity> {

    @Query("SELECT * FROM ${DodamTable.BANNER}")
    suspend fun getActiveBanners(): List<BannerEntity>

    @Query("DELETE FROM ${DodamTable.BANNER}")
    suspend fun deleteAllBanner()
}
