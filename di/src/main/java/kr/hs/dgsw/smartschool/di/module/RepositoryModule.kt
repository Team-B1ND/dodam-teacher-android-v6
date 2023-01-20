package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.MealRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository
}
