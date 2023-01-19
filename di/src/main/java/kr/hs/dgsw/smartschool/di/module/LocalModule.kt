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

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDodamTeacherDatabase(
        @ApplicationContext context: Context,
    ): kr.hs.dgsw.smartschool.local.database.DodamTeacherDatabase = Room
        .databaseBuilder(
            context,
            kr.hs.dgsw.smartschool.local.database.DodamTeacherDatabase::class.java,
            "dodam_database"
        )
        .build()

    @Provides
    fun provideMealDao(
        dodamTeacherDatabase: kr.hs.dgsw.smartschool.local.database.DodamTeacherDatabase
    ): kr.hs.dgsw.smartschool.local.dao.MealDao = dodamTeacherDatabase.mealDao()

}
