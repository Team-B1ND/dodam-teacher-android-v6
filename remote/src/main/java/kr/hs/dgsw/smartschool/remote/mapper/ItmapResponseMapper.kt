package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.model.itmap.CompanyUser
import kr.hs.dgsw.smartschool.remote.response.itmap.CompanyResponse
import kr.hs.dgsw.smartschool.remote.response.itmap.CompanyUserResponse

internal fun List<CompanyResponse>.toModel(): List<Company> =
    this.map {
        it.toModel()
    }

internal fun CompanyResponse.toModel(): Company =
    Company(
        id = id,
        name = name,
        address = address,
        companyUser = users?.toCompanyUsers(),
        longitude = longitude,
        latitude = latitude,
        symbolLogo = symbolLogo,
        textLogo = textLogo,
    )

internal fun List<CompanyUserResponse>.toCompanyUsers(): List<CompanyUser> =
    this.map {
        it.toModel()
    }

internal fun CompanyUserResponse.toModel(): CompanyUser =
    CompanyUser(
        position = field,
        generation = generation,
        githubId = githubId,
        id = id,
        image = image,
        info = info,
        name = name,
    )
