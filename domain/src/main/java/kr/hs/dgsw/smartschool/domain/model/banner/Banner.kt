package kr.hs.dgsw.smartschool.domain.model.banner

import java.time.LocalDate

data class Banner(
    val bannerOrder: Int,
    val createdDate: LocalDate,
    val expiryDateTime: LocalDate,
    val id: Int,
    val image: String,
    val url: String,
    val status: BannerStatus,
    val title: String,
)
