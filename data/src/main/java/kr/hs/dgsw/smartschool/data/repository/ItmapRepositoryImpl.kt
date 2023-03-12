package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository
import javax.inject.Inject

class ItmapRepositoryImpl @Inject constructor(
    override val remote: ItmapRemoteDataSource,
    override val cache: ItmapCacheDataSource,
) : BaseRepository<ItmapRemoteDataSource, ItmapCacheDataSource>, ItmapRepository {

    override suspend fun setCompanies(): List<Company> =
        remote.getCompanies().apply {
            cache.deleteCompanies()
            cache.insertCompanies(this)
        }

    override suspend fun getCompanies(): List<Company> =
        cache.getCompanies().ifEmpty {
            remote.getCompanies().apply {
                cache.deleteCompanies()
                cache.insertCompanies(this)
            }
        }

    override suspend fun getCompany(id: Int): Company =
        remote.getCompany(id)
}
