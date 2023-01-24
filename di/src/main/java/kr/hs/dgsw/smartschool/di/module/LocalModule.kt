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
}
