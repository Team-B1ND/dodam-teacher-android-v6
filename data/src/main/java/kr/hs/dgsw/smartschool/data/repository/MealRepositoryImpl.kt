package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import java.time.LocalDate
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    override val remote: MealRemoteDataSource,
    override val cache: MealCacheDataSource
) : BaseRepository<MealRemoteDataSource, MealCacheDataSource>, MealRepository {

    override suspend fun getMeal(date: LocalDate): Meal =
        cache.getMeal(
            date.year,
            date.monthValue,
            date.dayOfMonth
        ) ?: getRemoteMealList(date.year, date.monthValue).mealList.find {
            it.date == date
        } ?: Meal(date)

    override suspend fun getCalorieOfMeal(): Calorie =
        remote.getCalorieOfMeal()

    private suspend fun getRemoteMealList(year: Int, month: Int): MealList =
        remote.getMealOfMonth(month, year).apply {
            cache.insertMealList(this)
        }
}
