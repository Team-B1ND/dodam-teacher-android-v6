package kr.hs.dgsw.smartschool.data.datasource.meal

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.cache.MealCache
import kr.hs.dgsw.smartschool.data.remote.MealRemote
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import java.time.LocalDate
import javax.inject.Inject

class MealDataSourceImpl @Inject constructor(
    override val remote: MealRemote,
    override val cache: MealCache,
) : BaseDataSource<MealRemote, MealCache>, MealDataSource {

    override suspend fun getMeal(date: LocalDate): Meal =
        cache.getMeal(
            date.year,
            date.monthValue,
            date.dayOfMonth
        ) ?: getRemoteMealList(date.year, date.monthValue).mealList.find {
            it.date == date
        } ?: Meal(date)

    override suspend fun getCalorie(): Calorie =
        remote.getCalorieOfMeal()

    private suspend fun getRemoteMealList(year: Int, month: Int): MealList =
        remote.getMealOfMonth(month, year).apply {
            cache.insertMealList(this)
        }
}
