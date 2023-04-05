package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.place.PlaceRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    override val remote: PlaceRemoteDataSource,
) : BaseRepository<PlaceRemoteDataSource, Any>, PlaceRepository {

    override val cache: Any
        get() = TODO("Not yet implemented")

    override suspend fun getPlaces(): List<Place> {
        return remote.getPlaces()
    }
}
