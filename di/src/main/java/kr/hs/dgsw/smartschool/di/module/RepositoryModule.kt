package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.BannerRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.ItmapRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MemberRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.NightStudyRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.OutRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.PlaceRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.PointRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.ScheduleRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.StudentRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.StudyRoomRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TeacherRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TimeTableRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import kr.hs.dgsw.smartschool.domain.repository.ItmapRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.repository.MemberRepository
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.repository.ScheduleRepository
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import kr.hs.dgsw.smartschool.domain.repository.TimeTableRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
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

    @Singleton
    @Binds
    abstract fun bindNightStudyRepository(
        nightStudyRepositoryImpl: NightStudyRepositoryImpl
    ): NightStudyRepository
}
