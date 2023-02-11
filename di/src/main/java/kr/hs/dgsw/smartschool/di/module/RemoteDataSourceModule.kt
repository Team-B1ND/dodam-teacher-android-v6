package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.remote.datasource.MealRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesMealRemote(
        mealRemoteDataSourceImpl: MealRemoteDataSourceImpl
    ): MealRemoteDataSource
}
