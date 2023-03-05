package kr.hs.dgsw.smartschool.remote.response.banner

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @field:SerializedName("bannerOrder") val bannerOrder: Int,
    @field:SerializedName("createdDate") val createdDate: String,
    @field:SerializedName("expiryDateTime") val expiryDateTime: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("redirectUrl") val redirectUrl: String,
    @field:SerializedName("status") val status: BannerResponseStatus,
    @field:SerializedName("title") val title: String,
)
