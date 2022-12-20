import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    const val APPLICATION_ID = "kr.hs.dgsw.smartschool.dodamdodam_teacher"
    const val NAME_SPACE_DOMAIN = "kr.hs.dgsw.smartschool.domain"
    const val NAME_SPACE_DATA = "kr.hs.dgsw.smartschool.data"
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

    const val JVM_TARGET = "1.8"

    const val KT_LINT = "10.2.0"
    const val COMPILE_SDK_VERSION = 33

    const val ANDROID_VERSION = "7.2.1"
    const val JETBRAINS_KOTLIN = "1.7.10"

    const val BUILD_FEATURE_COMPOSE = true

    const val PATH_DATA = ":data"
    const val PATH_DOMAIN = ":domain"
    const val PATH_PRESENTATION = ":presentation"
}
