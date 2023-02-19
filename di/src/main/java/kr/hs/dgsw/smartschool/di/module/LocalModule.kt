package kr.hs.dgsw.smartschool.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.database.DodamTeacherDatabase
import javax.inject.Singleton
import kr.hs.dgsw.smartschool.local.dao.AccountDao
import kr.hs.dgsw.smartschool.local.dao.ClassroomDao
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.ParentDao
import kr.hs.dgsw.smartschool.local.dao.PlaceDao
import kr.hs.dgsw.smartschool.local.dao.StudentDao
import kr.hs.dgsw.smartschool.local.dao.TeacherDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao

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
            "dodam_database"
        )
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
}
