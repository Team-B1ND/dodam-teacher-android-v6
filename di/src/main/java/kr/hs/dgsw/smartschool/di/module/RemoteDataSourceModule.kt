package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.itmap.ItmapRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.member.MemberRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.place.PlaceRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.point.PointRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.remote.datasource.*
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
    abstract fun providesClassroomRemoteDataSource(
        classroomRemoteDataSourceImpl: ClassroomRemoteDataSourceImpl
    ): ClassroomRemoteDataSource

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
}
