package kr.hs.dgsw.smartschool.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.di.qualifier.AuthOkhttpClient
import kr.hs.dgsw.smartschool.di.qualifier.AuthRetrofit
import kr.hs.dgsw.smartschool.di.qualifier.BasicRetrofit
import kr.hs.dgsw.smartschool.di.qualifier.OkhttpClient
import kr.hs.dgsw.smartschool.remote.interceptor.TokenInterceptor
import kr.hs.dgsw.smartschool.remote.service.AuthService
import kr.hs.dgsw.smartschool.remote.service.MealService
import kr.hs.dgsw.smartschool.remote.service.OutService
import kr.hs.dgsw.smartschool.remote.service.TokenService
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
        return gsonBuilder.create()
    }

    @AuthOkhttpClient
    @Provides
    @Singleton
    fun provideAuthClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @OkhttpClient
    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    @BasicRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, @OkhttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(DodamUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(gson: Gson, @AuthOkhttpClient okHttpAuthClint: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpAuthClint)
            .baseUrl(DodamUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesMealService(@BasicRetrofit retrofit: Retrofit): MealService =
        retrofit.create(MealService::class.java)

    @Singleton
    @Provides
    fun providesAuthService(@AuthRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providesOutService(@BasicRetrofit retrofit: Retrofit): OutService =
        retrofit.create(OutService::class.java)

    @Singleton
    @Provides
    fun providesTokenService(@AuthRetrofit retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)
}
