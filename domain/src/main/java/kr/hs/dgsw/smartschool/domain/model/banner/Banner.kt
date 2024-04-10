package kr.hs.dgsw.smartschool.domain.model.banner

import java.time.LocalDateTime

data class Banner(
    val expiryDateTime: LocalDateTime,
    val id: Int,
    val image: String,
    val url: String,
    val status: BannerStatus,
    val title: String,
)
