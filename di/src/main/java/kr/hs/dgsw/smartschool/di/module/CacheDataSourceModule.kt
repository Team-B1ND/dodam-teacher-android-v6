package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.AuthCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.MealCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.OutCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.TokenCacheDataSourceImpl
import javax.inject.Singleton

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

    @Singleton
    @Binds
    abstract fun provideOutCacheDataSource(
        outCacheDataSourceImpl: OutCacheDataSourceImpl
    ): OutCacheDataSource

    @Singleton
    @Binds
    abstract fun provideTokenCacheDataSource(
        tokenCacheDataSourceImpl: TokenCacheDataSourceImpl
    ): TokenCacheDataSource
}
