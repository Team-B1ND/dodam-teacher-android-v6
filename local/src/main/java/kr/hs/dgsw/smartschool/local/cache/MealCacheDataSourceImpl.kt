package kr.hs.dgsw.smartschool.local.cache

import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealList
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class MealCacheDataSourceImpl @Inject constructor(
    private val mealDao: MealDao,
) : MealCacheDataSource {

    override suspend fun getAllMeal(): MealList {
        val mealEntityList = mealDao.getAllMeal()
        return if (mealEntityList.isEmpty()) MealList(emptyList()) else mealEntityList.toModel()
    }

    override suspend fun getMealByMonth(year: Int, month: Int): MealList {
        val mealEntityList = mealDao.getMealByMonth(year, month)
        return if (mealEntityList.isEmpty()) MealList(emptyList()) else mealEntityList.toModel()
    }

    override suspend fun getMeal(year: Int, month: Int, day: Int): Meal? =
        mealDao.getMeal(year, month, day)?.toModel()

    override suspend fun insertMealList(mealList: MealList) {
        mealDao.insert(mealList.toEntity())
    }

    override suspend fun deleteAllMeal() =
        mealDao.deleteAllMeal()
}
