package kr.hs.dgsw.smartschool.data.datasource.itmap

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

interface ItmapRemoteDataSource {

    suspend fun getCompanies(): List<Company>

    suspend fun getCompany(id: Int): Company
}