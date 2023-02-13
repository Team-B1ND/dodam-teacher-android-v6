package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.MealCacheDataSourceImpl
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.AuthCacheDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesMealCacheDataSource(
        mealCacheDataSourceImpl: MealCacheDataSourceImpl
    ): MealCacheDataSource

    @Singleton
    @Binds
    abstract fun provideAuthCacheDataSource(
        authCacheDataSourceImpl: AuthCacheDataSourceImpl
    ): AuthCacheDataSource
}
