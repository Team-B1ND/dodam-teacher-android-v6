package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateTimeToLocalDateT
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.banner.BannerStatus
import kr.hs.dgsw.smartschool.remote.response.banner.BannerResponse
import kr.hs.dgsw.smartschool.remote.response.banner.BannerResponseStatus

internal fun List<BannerResponse>.toModel(): List<Banner> =
    this.map {
        it.toModel()
    }

internal fun BannerResponse.toModel(): Banner =
    Banner(
        expiryDateTime = expiryDateTime.yearDateTimeToLocalDateT(),
        id = id,
        image = image,
        url = redirectUrl,
        status = status.toBannerStatus(),
        title = title,
    )

internal fun BannerResponseStatus.toBannerStatus(): BannerStatus =
    when (this.name) {
        BannerStatus.ACTIVE.name -> BannerStatus.ACTIVE
        BannerStatus.DEACTIVATED.name -> BannerStatus.DEACTIVATED
        else -> BannerStatus.DEACTIVATED
    }
