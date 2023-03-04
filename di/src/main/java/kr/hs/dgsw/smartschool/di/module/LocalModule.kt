package kr.hs.dgsw.smartschool.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.local.dao.AccountDao
import kr.hs.dgsw.smartschool.local.dao.ClassroomDao
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.OutDao
import kr.hs.dgsw.smartschool.local.dao.ParentDao
import kr.hs.dgsw.smartschool.local.dao.PlaceDao
import kr.hs.dgsw.smartschool.local.dao.StudentDao
import kr.hs.dgsw.smartschool.local.dao.TeacherDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.database.DodamTeacherDatabase
import kr.hs.dgsw.smartschool.local.database.MIGRATION_1_TO_2
import kr.hs.dgsw.smartschool.local.table.DodamTable
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.domain.model.banner.Banner
import kr.hs.dgsw.smartschool.local.dao.BannerDao

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDodamTeacherDatabase(
        @ApplicationContext context: Context,
    ): DodamTeacherDatabase = Room
        .databaseBuilder(
            context,
            DodamTeacherDatabase::class.java,
            DodamTable.DATABASE
        )
        .addMigrations(MIGRATION_1_TO_2)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMealDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): MealDao = dodamTeacherDatabase.mealDao()

    @Provides
    @Singleton
    fun provideClassroomDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): ClassroomDao = dodamTeacherDatabase.classroomDao()

    @Provides
    @Singleton
    fun provideMemberDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): MemberDao = dodamTeacherDatabase.memberDao()

    @Provides
    @Singleton
    fun provideParentDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): ParentDao = dodamTeacherDatabase.parentDao()

    @Provides
    @Singleton
    fun providePlaceDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): PlaceDao = dodamTeacherDatabase.placeDao()

    @Provides
    @Singleton
    fun provideStudentDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): StudentDao = dodamTeacherDatabase.studentDao()

    @Provides
    @Singleton
    fun provideTeacherDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): TeacherDao = dodamTeacherDatabase.teacherDao()

    @Provides
    @Singleton
    fun provideTokenDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): TokenDao = dodamTeacherDatabase.tokenDao()

    @Provides
    @Singleton
    fun provideAccountDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): AccountDao = dodamTeacherDatabase.accountDao()

    @Provides
    @Singleton
    fun provideOutDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): OutDao = dodamTeacherDatabase.outDao()

    @Provides
    @Singleton
    fun provideBannerDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): BannerDao = dodamTeacherDatabase.bannerDao()
}
