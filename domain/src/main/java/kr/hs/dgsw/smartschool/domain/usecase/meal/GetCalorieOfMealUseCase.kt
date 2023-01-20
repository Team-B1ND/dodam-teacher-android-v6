package kr.hs.dgsw.smartschool.domain.usecase.meal

import javax.inject.Inject
import kr.hs.dgsw.smartschool.domain.repository.MealRepository

class GetCalorieOfMealUseCase @Inject constructor(
    private val repository: MealRepository,
) {
    
    suspend operator fun invoke() = kotlin.runCatching {
        repository.getCalorieOfMeal()
    }
}