package kr.hs.dgsw.smartschool.remote.response.itmap

import com.google.gson.annotations.SerializedName

data class CompanyUserResponse(
    @field:SerializedName("field") val field: String,
    @field:SerializedName("generation") val generation: Int,
    @field:SerializedName("githubId") val githubId: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("info") val info: String,
    @field:SerializedName("name") val name: String,
)
