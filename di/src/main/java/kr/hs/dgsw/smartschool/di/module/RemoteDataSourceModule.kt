package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.night_study.NightStudyRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.place.PlaceRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.point.PointRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.remote.datasource.AuthRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.BannerRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.ItmapRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.MealRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.MemberRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.NightStudyRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.OutRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.PlaceRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.PointRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.ScheduleRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.StudyRoomRemoteDataSourceImpl
import kr.hs.dgsw.smartschool.remote.datasource.TimeTableRemoteDataSourceImpl
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
    abstract fun providesTokenRemoteDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ): TokenRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesBannerRemoteDataSource(
        bannerRemoteDataSourceImpl: BannerRemoteDataSourceImpl
    ): BannerRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesPointRemoteDataSource(
        pointRemoteDataSourceImpl: PointRemoteDataSourceImpl
    ): PointRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesMemberRemoteDataSource(
        memberRemoteDataSourceImpl: MemberRemoteDataSourceImpl
    ): MemberRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesScheduleRemoteDataSource(
        scheduleRemoteDataSourceImpl: ScheduleRemoteDataSourceImpl
    ): ScheduleRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesItmapRemoteDataSource(
        itmapRemoteDataSourceImpl: ItmapRemoteDataSourceImpl
    ): ItmapRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesStudyRoomRemoteDataSource(
        studyRoomRemoteDataSourceImpl: StudyRoomRemoteDataSourceImpl
    ): StudyRoomRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesPlaceRemoteDataSource(
        placeRemoteDataSourceImpl: PlaceRemoteDataSourceImpl
    ): PlaceRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesTimeTableRemoteDataSource(
        timeTableRemoteDataSourceImpl: TimeTableRemoteDataSourceImpl
    ): TimeTableRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindNightStudyRemoteDataSource(
        nightStudyRemoteDataSourceImpl: NightStudyRemoteDataSourceImpl
    ): NightStudyRemoteDataSource
}
