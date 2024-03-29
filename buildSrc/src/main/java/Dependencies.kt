object Kotlin {
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.SPLASH_SCREEN}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
}

object Google {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT_ANDROID_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    const val GOOGLE_LOCATION = "com.google.android.gms:play-services-location:${Versions.GOOGLE_LOCATION}"

    const val GOOGLE_GMS = "com.google.gms:google-services:${Versions.GOOGLE_GMS}"

    const val EXO_PLAYER_CORE = "com.google.android.exoplayer:exoplayer-core:${Versions.EXO_PLAYER}"
    const val EXO_PLAYER_UI = "com.google.android.exoplayer:exoplayer-ui:${Versions.EXO_PLAYER}"
    const val EXO_PLAYER_DASH = "com.google.android.exoplayer:exoplayer-dash:${Versions.EXO_PLAYER}"
}

object Libraries {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val GLIDE_LANDSCAPIST = "com.github.skydoves:landscapist-glide:${Versions.GLIDE_LANDSCAPIST}"

    const val CIRCULAR_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.CIRCULAR_IMAGE_VIEW}"
    const val INDICATOR = "com.tbuonomo:dotsindicator:${Versions.INDICATOR}"
    const val VIEW_PAGER = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER}"

    const val LOTTIE = "com.airbnb.android:lottie-compose:${Versions.LOTTIE}"

    const val NAVER_MAP_COMPOSE = "io.github.fornewid:naver-map-compose:${Versions.NAVER_MAP_COMPOSE}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object NaverMap {
    const val NAVER_MAP = "com.naver.maps:map-sdk:${Versions.NAVER_MAP}"
}

object Compose {
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val UI_COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val MATERIAL_COMPOSE = "androidx.compose.material:material:${Versions.MATERIAL_COMPOSE}"
    const val COMPOSE_HILT = "androidx.hilt:hilt-navigation-compose:${Versions.COMPOSE_HILT}"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
    const val UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_VERSION}"
    const val SYSTEM_UI_CONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:${Versions.SYSTEM_UI_CONTROLLER}"

    const val PAGER_COMPOSE = "com.google.accompanist:accompanist-pager:${Versions.PAGER_COMPOSE}"
    const val PAGER_INDICATORS_COMPOSE = "com.google.accompanist:accompanist-pager-indicators:${Versions.PAGER_COMPOSE}"

    const val DUI = "com.github.Team-B1ND:dui-android:${Versions.DUI}"
}

object OrbitMVI {
    const val ORBIT_CORE = "org.orbit-mvi:orbit-core:${Versions.ORBIT}"
    const val ORBIT_VIEWMODEL = "org.orbit-mvi:orbit-viewmodel:${Versions.ORBIT}"
    const val ORBIT_COMPOSE = "org.orbit-mvi:orbit-compose:${Versions.ORBIT}"
    const val ORBIT_TEST = "org.orbit-mvi:orbit-test:${Versions.ORBIT}"
}