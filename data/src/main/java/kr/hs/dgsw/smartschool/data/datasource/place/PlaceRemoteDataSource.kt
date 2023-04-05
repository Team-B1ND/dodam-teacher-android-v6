package kr.hs.dgsw.smartschool.data.datasource.place

import kr.hs.dgsw.smartschool.domain.model.place.Place

interface PlaceRemoteDataSource {

    suspend fun getPlaces(): List<Place>
}
