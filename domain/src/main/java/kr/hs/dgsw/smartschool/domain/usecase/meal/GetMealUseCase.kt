package kr.hs.dgsw.smartschool.domain.usecase.meal

import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import java.time.LocalDate
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val mealRepository: MealRepository,
) {

    suspend operator fun invoke(date: LocalDate) = kotlin.runCatching {
        mealRepository.getMeal(date)
    }
}
