package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.place.PlaceRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.service.BannerService
import kr.hs.dgsw.smartschool.remote.service.PlaceService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val placeService: PlaceService
) : PlaceRemoteDataSource {

    override suspend fun getPlaces(): List<Place>
    = placeService.getPlace().data.toModel()
}
