package kr.hs.dgsw.smartschool.domain.model.itmap

data class Company(
    val id: Int,
    val name: String,
    val address: String,
    val companyUser: List<CompanyUser>?,
    val longitude: Double,
    val latitude: Double,
    val symbolLogo: String,
    val textLogo: String,
)
