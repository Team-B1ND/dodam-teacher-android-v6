package kr.hs.dgsw.smartschool.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClient