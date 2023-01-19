package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.cache.MealCache
import kr.hs.dgsw.smartschool.data.datasource.meal.MealDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealDataSourceImpl
import kr.hs.dgsw.smartschool.local.cache.MealCacheImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun providesMealDataSource(mealDataSourceImpl: MealDataSourceImpl): MealDataSource
}
