package kr.hs.dgsw.smartschool.local.mapper

import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.local.entity.place.PlaceEntity

internal fun PlaceEntity.toModel(): Place =
    Place(
        id = id,
        name = name,
        type = Place.PlaceType(
            id = placeTypeId,
            name = placeTypeName,
        )
    )
