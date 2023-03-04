package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.data.utils.yearDateToLocalDate
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.banner.BannerStatus
import kr.hs.dgsw.smartschool.local.entity.banner.BannerEntity

internal fun List<Banner>.toEntity(): List<BannerEntity> =
    this.map {
        it.toEntity()
    }

internal fun List<BannerEntity>.toModel(): List<Banner> =
    this.map {
        it.toModel()
    }

internal fun BannerEntity.toModel(): Banner =
    Banner(
        id = id,
        bannerOrder = bannerOrder,
        createdDate = createdDate.yearDateToLocalDate(),
        expiryDateTime = expiryDateTime.yearDateToLocalDate(),
        image = image,
        url = url,
        status = status.toBannerStatus(),
        title = title,
    )

internal fun Banner.toEntity(): BannerEntity =
    BannerEntity(
        id = id,
        bannerOrder = bannerOrder,
        createdDate = createdDate.toString(),
        expiryDateTime = expiryDateTime.toString(),
        image = image,
        url = url,
        status = status.name,
        title = title,
    )

internal fun String.toBannerStatus(): BannerStatus = when(this) {
    BannerStatus.ACTIVE.name -> BannerStatus.ACTIVE
    BannerStatus.DEACTIVATED.name -> BannerStatus.DEACTIVATED
    else -> BannerStatus.ACTIVE
}