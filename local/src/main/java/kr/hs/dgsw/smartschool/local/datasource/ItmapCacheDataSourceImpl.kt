package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.local.dao.ItmapDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel

class ItmapCacheDataSourceImpl @Inject constructor(
    private val itmapDao: ItmapDao,
) : ItmapCacheDataSource {

    override suspend fun getCompanies(): List<Company> =
        itmapDao.getCompanies().toModel()

    override suspend fun insertCompanies(companies: List<Company>) =
        itmapDao.insert(companies.toEntity())

    override suspend fun deleteCompanies() =
        itmapDao.deleteCompanies()
}
