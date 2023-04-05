package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.local.entity.itmap.CompanyEntity

internal fun List<CompanyEntity>.toModel(): List<Company> =
    this.map {
        it.toModel()
    }

internal fun CompanyEntity.toModel(): Company =
    Company(
        id = id,
        name = name,
        address = address,
        companyUser = emptyList(),
        longitude = longitude,
        latitude = latitude,
        symbolLogo = symbolLogo,
        textLogo = textLogo,
    )

internal fun List<Company>.toEntity(): List<CompanyEntity> =
    this.map {
        it.toEntity()
    }

internal fun Company.toEntity(): CompanyEntity =
    CompanyEntity(
        id = id,
        name = name,
        address = address,
        longitude = longitude,
        latitude = latitude,
        symbolLogo = symbolLogo,
        textLogo = textLogo,
    )
