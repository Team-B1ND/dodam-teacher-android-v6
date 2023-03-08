package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.datasource.auth.AuthCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.banner.BannerCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.meal.MealCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.token.TokenCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.AuthCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.BannerCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.MealCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.OutCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.TokenCacheDataSourceImpl
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.data.datasource.classroom.ClassroomCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.member.MemberCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.teacher.TeacherCacheDataSource
import kr.hs.dgsw.smartschool.local.datasource.ClassroomCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.MemberCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.StudentCacheDataSourceImpl
import kr.hs.dgsw.smartschool.local.datasource.TeacherCacheDataSourceImpl

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
    abstract fun provideClassroomCacheDataSource(
        classroomCacheDataSourceImpl: ClassroomCacheDataSourceImpl
    ): ClassroomCacheDataSource

    @Singleton
    @Binds
    abstract fun provideMemberCacheDataSource(
        memberCacheDataSourceImpl: MemberCacheDataSourceImpl
    ): MemberCacheDataSource

    @Singleton
    @Binds
    abstract fun provideTeacherCacheDataSource(
        teacherCacheDataSourceImpl: TeacherCacheDataSourceImpl
    ): TeacherCacheDataSource

    @Singleton
    @Binds
    abstract fun provideStudentCacheDataSource(
        studentCacheDataSourceImpl: StudentCacheDataSourceImpl
    ): StudentCacheDataSource
}
