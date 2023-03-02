package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.data.repository.OutRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesMealRepository(
        mealRepositoryImpl: MealRepositoryImpl
    ): MealRepository

    @Singleton
    @Binds
    abstract fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun providesOutRepository(
        outRepositoryImpl: OutRepositoryImpl
    ): OutRepository

    @Binds
    abstract fun providesTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository
}
