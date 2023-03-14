package kr.hs.dgsw.smartschool.remote.mapper

import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.remote.response.place.PlaceResponse

internal fun PlaceResponse.toModel(): Place =
    Place(
        id = id,
        name = name,
        type = type.toModel(),
    )

internal fun List<PlaceResponse>.toModel(): List<Place>{
    return this.map {
        it.toModel()
    }
}
internal fun PlaceResponse.PlaceType.toModel(): Place.PlaceType =
    Place.PlaceType(
        id = id,
        name = name,
    )
