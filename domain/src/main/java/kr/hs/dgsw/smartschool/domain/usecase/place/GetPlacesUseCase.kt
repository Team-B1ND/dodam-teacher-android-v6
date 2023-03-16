package kr.hs.dgsw.smartschool.domain.usecase.place

import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        placeRepository.getPlaces()
    }
}
