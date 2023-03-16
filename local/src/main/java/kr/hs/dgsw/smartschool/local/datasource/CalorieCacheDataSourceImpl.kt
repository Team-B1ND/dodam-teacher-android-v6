package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.calorie.CalorieCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.local.dao.CalorieDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class CalorieCacheDataSourceImpl @Inject constructor(
    private val calorieDao: CalorieDao,
) : CalorieCacheDataSource {

    override suspend fun getCalorieByMonth(year: Int, month: Int): List<Calorie> =
        calorieDao.getCalorieByMonth(year, month).toModel()

    override suspend fun getCalorie(year: Int, month: Int, day: Int): Calorie? =
        calorieDao.getCalorie(year, month, day)?.toModel()

    override suspend fun insertCalories(calories: List<Calorie>) =
        calorieDao.insert(calories.toEntity())

    override suspend fun insertCalorie(calorie: Calorie) =
        calorieDao.insert(calorie.toEntity())

    override suspend fun deleteAllCalorie() =
        calorieDao.deleteAllCalorie()
}
