package kr.hs.dgsw.smartschool.local.cache

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.cache.MealCache
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.mapper.toModel

class MealCacheImpl @Inject constructor(
    private val mealDao: MealDao,
) : MealCache {

    override suspend fun getAllMeal(): List<Meal> {
        val mealEntityList = mealDao.getAllMeal()
        return if (mealEntityList.isEmpty()) emptyList() else mealEntityList.toModel()
    }

    override suspend fun getMealByMonth(year: Int, month: Int): List<Meal> {
        val mealEntityList = mealDao.getMealByMonth(year, month)
        return if (mealEntityList.isEmpty()) emptyList() else mealEntityList.toModel()
    }

    override suspend fun getMeal(year: Int, month: Int, day: Int): Meal? =
        mealDao.getMeal(year, month, day)?.toModel()


    override suspend fun deleteAllMeal() =
        mealDao.deleteAllMeal()
}
