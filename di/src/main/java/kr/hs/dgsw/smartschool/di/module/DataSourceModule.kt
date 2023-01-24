package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.meal.MealDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun providesMealDataSource(
        mealDataSourceImpl: MealDataSourceImpl
    ): MealDataSource
}
