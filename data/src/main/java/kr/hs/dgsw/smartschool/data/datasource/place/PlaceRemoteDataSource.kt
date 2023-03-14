package kr.hs.dgsw.smartschool.data.datasource.place

import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.place.Place

interface PlaceRemoteDataSource {

    suspend fun getPlaces(): List<Place>
}