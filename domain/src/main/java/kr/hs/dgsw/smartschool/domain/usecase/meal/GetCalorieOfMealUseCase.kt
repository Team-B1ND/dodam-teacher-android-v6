package kr.hs.dgsw.smartschool.domain.usecase.meal

import java.time.LocalDate
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import javax.inject.Inject

class GetCalorieOfMealUseCase @Inject constructor(
    private val mealRepository: MealRepository,
) {

    suspend operator fun invoke(date: LocalDate) = kotlin.runCatching {
        mealRepository.getCalorieOfMeal(date)
    }
}
