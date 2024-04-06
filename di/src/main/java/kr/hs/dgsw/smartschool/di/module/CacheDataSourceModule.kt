package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.calorie.CalorieCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.member.MemberCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.AuthCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.BannerCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.CalorieCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.ItmapCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.MealCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.MemberCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.OutCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.ScheduleCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.TimeTableCacheDataSourceImpl
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

    @Singleton
    @Binds
    abstract fun provideBannerCacheDataSource(
        bannerCacheDataSourceImpl: BannerCacheDataSourceImpl
    ): BannerCacheDataSource

    @Singleton
    @Binds
    abstract fun provideMemberCacheDataSource(
        memberCacheDataSourceImpl: MemberCacheDataSourceImpl
    ): MemberCacheDataSource



    @Singleton
    @Binds
    abstract fun provideScheduleCacheDataSource(
        scheduleCacheDataSourceImpl: ScheduleCacheDataSourceImpl
    ): ScheduleCacheDataSource

    @Singleton
    @Binds
    abstract fun provideItmapCacheDataSource(
        itmapCacheDataSourceImpl: ItmapCacheDataSourceImpl,
    ): ItmapCacheDataSource

    @Singleton
    @Binds
    abstract fun provideCalorieCacheDataSource(
        calorieCacheDataSourceImpl: CalorieCacheDataSourceImpl,
    ): CalorieCacheDataSource

    @Singleton
    @Binds
    abstract fun provideTimeTableCacheDataSource(
        timeTableCacheDataSourceImpl: TimeTableCacheDataSourceImpl,
    ): TimeTableCacheDataSource
}
