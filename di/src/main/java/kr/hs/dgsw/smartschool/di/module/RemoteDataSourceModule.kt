package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.remote.datasource.AuthRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.BannerRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.MealRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.OutRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.TokenRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesMealRemoteDataSource(
        mealRemoteDataSourceImpl: MealRemoteDataSourceImpl
    ): MealRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesOutRemoteDataSource(
        outRemoteDataSourceImpl: OutRemoteDataSourceImpl
    ): OutRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesTokenDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ): TokenRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesBannerDataSource(
        bannerRemoteDataSourceImpl: BannerRemoteDataSourceImpl
    ): BannerRemoteDataSource
}
