package kr.hs.dgsw.smartschool.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.remote.MealRemote
import kr.hs.dgsw.smartschool.remote.datasource.MealRemoteImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun providesMealRemote(mealRemoteImpl: MealRemoteImpl): MealRemote

}