package kr.hs.dgsw.smartschool.remote.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.ItmapService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall

class ItmapRemoteDataSourceImpl @Inject constructor(
    private val itmapService: ItmapService,
) : ItmapRemoteDataSource {

    override suspend fun getCompanies(): List<Company> = dodamApiCall {
        itmapService.getCompanies().data.toModel()
    }

    override suspend fun getCompany(id: Int): Company = dodamApiCall {
        itmapService.getCompany(id).data.toModel()
    }
}
