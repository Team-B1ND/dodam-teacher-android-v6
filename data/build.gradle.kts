plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
    id(Plugins.kotlinParcelize)
    id(Plugins.kt_lint) version Versions.KT_LINT
}

android {
    namespace = ProjectProperties.NAME_SPACE_DATA
    compileSdk = ProjectProperties.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.MIN_SDK_VERSION
        targetSdk = ProjectProperties.TARGET_SDK_VERSION

        testInstrumentationRunner = ProjectProperties.TEST_RUNNER
    }

    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.JVM_TARGET
    }
    packagingOptions {
        resources {
            excludes += ProjectProperties.EXCLUDES
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)
    implementation(Compose.ACTIVITY_COMPOSE)
    implementation(Compose.UI_COMPOSE)
    implementation(Compose.UI_TOOLING_PREVIEW)
    implementation(Compose.MATERIAL_COMPOSE)
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Compose.UI_TEST_JUNIT4)
    debugImplementation(Compose.UI_TOOLING)
    debugImplementation(Compose.UI_TEST_MANIFEST)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // room
    implementation(AndroidX.ROOM_RUNTIME)
    kapt(AndroidX.ROOM_COMPILER)
    implementation(AndroidX.ROOM_KTX)

    implementation(project(ProjectProperties.PATH_DOMAIN))
}
