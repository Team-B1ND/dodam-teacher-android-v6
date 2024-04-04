package kr.hs.dgsw.smartschool.remote.response.banner

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @field:SerializedName("expireAt") val expiryDateTime: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("imageUrl") val image: String,
    @field:SerializedName("redirectUrl") val redirectUrl: String,
    @field:SerializedName("status") val status: BannerResponseStatus,
    @field:SerializedName("title") val title: String,
)
