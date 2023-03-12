package kr.hs.dgsw.smartschool.data.datasource.itmap

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

interface ItmapCacheDataSource {

    suspend fun getCompanies(): List<Company>

    suspend fun insertCompanies(companies: List<Company>)

    suspend fun deleteCompanies()
}