package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.data.cache.MealCache
import kr.hs.dgsw.smartschool.local.cache.MealCacheImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Singleton
    @Binds
    abstract fun providesMealCache(
        mealCacheImpl: MealCacheImpl
    ): MealCache
}
