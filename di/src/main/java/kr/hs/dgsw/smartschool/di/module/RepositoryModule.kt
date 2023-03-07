package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.BannerRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.OutRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.StudyRoomRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.repository.BannerRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.data.repository.ClassroomRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.PointRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.ClassroomRepository
import kr.hs.dgsw.smartschool.domain.repository.PointRepository

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
}
