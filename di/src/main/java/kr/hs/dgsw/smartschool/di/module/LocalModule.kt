package kr.hs.dgsw.smartschool.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.local.dao.MealDao
import kr.hs.dgsw.smartschool.data.local.database.DodamTeacherDatabase

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
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
    fun provideMealDao(
        dodamTeacherDatabase: DodamTeacherDatabase
    ): MealDao = dodamTeacherDatabase.mealDao()

}
