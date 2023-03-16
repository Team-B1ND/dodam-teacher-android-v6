package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.*
import kr.hs.dgsw.smartschool.domain.repository.*
import javax.inject.Singleton

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

    @Singleton
    @Binds
    abstract fun providesTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

    @Singleton
    @Binds
    abstract fun providesBannerRepository(
        bannerRepositoryImpl: BannerRepositoryImpl
    ): BannerRepository

    @Singleton
    @Binds
    abstract fun providesPointRepository(
        pointRepositoryImpl: PointRepositoryImpl
    ): PointRepository

    @Singleton
    @Binds
    abstract fun providesClassroomRepository(
        classroomRepositoryImpl: ClassroomRepositoryImpl
    ): ClassroomRepository

    @Singleton
    @Binds
    abstract fun providesStudyRoomRepository(
        studyRoomRepositoryImpl: StudyRoomRepositoryImpl
    ): StudyRoomRepository

    @Singleton
    @Binds
    abstract fun providesMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository

    @Singleton
    @Binds
    abstract fun providesTeacherRepository(
        teacherRepositoryImpl: TeacherRepositoryImpl
    ): TeacherRepository

    @Singleton
    @Binds
    abstract fun providesStudentRepository(
        studentRepositoryImpl: StudentRepositoryImpl
    ): StudentRepository

    @Singleton
    @Binds
    abstract fun providesScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository

    @Singleton
    @Binds
    abstract fun providesItmapRepository(
        itmapRepositoryImpl: ItmapRepositoryImpl
    ): ItmapRepository

    @Singleton
    @Binds
    abstract fun providesPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository

    @Singleton
    @Binds
    abstract fun providesTimeTableRepository(
        timeTableRepositoryImpl: TimeTableRepositoryImpl
    ): TimeTableRepository
}
