package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

interface ItmapRepository {

    suspend fun setCompanies(): List<Company>

    suspend fun getCompanies(): List<Company>

    suspend fun getCompany(id: Int): Company
}
