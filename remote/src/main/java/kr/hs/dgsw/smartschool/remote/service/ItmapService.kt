package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.itmap.CompanyResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET
import retrofit2.http.Path

interface ItmapService {

    @GET(DodamUrl.Itmap.COMPANIES)
    suspend fun getCompanies(): Response<List<CompanyResponse>>

    @GET(DodamUrl.Itmap.COMPANY_ID)
    suspend fun getCompany(
        @Path("id") id: Int
    ): Response<CompanyResponse>
}