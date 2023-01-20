package kr.hs.dgsw.smartschool.domain.usecase.meal

import java.time.LocalDate
import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.MealRepository

class GetMealUseCase @Inject constructor(
    private val mealRepository: MealRepository,
) {

    suspend operator fun invoke(date: LocalDate) = kotlin.runCatching {
        mealRepository.getMeal(date)
    }
}