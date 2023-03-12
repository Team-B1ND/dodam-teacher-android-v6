package kr.hs.dgsw.smartschool.remote.response.itmap

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("companyPlaceId") val companyPlaceId: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("address") val address: String,
    @field:SerializedName("users") val users: List<CompanyUserResponse>,
    @field:SerializedName("longitude") val longitude: Double,
    @field:SerializedName("latitude") val latitude: Double,
    @field:SerializedName("symbolLogo") val symbolLogo: String,
    @field:SerializedName("textLogo") val textLogo: String,
)
