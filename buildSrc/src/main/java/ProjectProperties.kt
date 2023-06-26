import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 29
    const val VERSION_NAME = "2.1.1"

    const val APPLICATION_ID = "kr.hs.dgsw.smartschool.dodamdodam_teacher"
    const val NAME_SPACE_DOMAIN = "kr.hs.dgsw.smartschool.domain"
    const val NAME_SPACE_DATA = "kr.hs.dgsw.smartschool.data"
    const val NAME_SPACE_LOCAL = "kr.hs.dgsw.smartschool.local"
    const val NAME_SPACE_DI = "kr.hs.dgsw.smartschool.di"
    const val NAME_SPACE_REMOTE = "kr.hs.dgsw.smartschool.remote"
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val PROGUARD_NAME = "proguard-android.txt"
    const val PROGUARD_FILE = "proguard-rules.pro"
    const val IS_MINIFY_ENABLE = false
    const val APPLICATION_BUILD_TYPE = "release"
    const val USE_SUPPORT_LIBRARY = true
    const val EXCLUDES = "/META-INF/{AL2.0,LGPL2.1}"

    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 33

    val JVM_TARGET = JavaVersion.VERSION_1_8.toString()

    const val KT_LINT = "10.2.0"
    const val COMPILE_SDK_VERSION = 33

    const val ANDROID_VERSION = "7.4.0"
    const val JETBRAINS_KOTLIN = "1.7.10"

    const val BUILD_FEATURE_COMPOSE = true

    const val PATH_LOCAL = ":local"
    const val PATH_REMOTE = ":remote"
    const val PATH_DATA = ":data"
    const val PATH_DOMAIN = ":domain"
    const val PATH_PRESENTATION = ":presentation"
    const val PATH_DI = ":di"
}
