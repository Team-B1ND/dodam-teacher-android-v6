buildscript {
    dependencies {
        classpath(Google.HILT_ANDROID_PLUGIN)
        classpath(Google.GOOGLE_GMS)
    }
}

plugins {
    id (Plugins.androidApplication).version(ProjectProperties.ANDROID_VERSION).apply(false)
    id (Plugins.androidLibrary).version(ProjectProperties.ANDROID_VERSION).apply(false)
    id (Plugins.jetbrainsKotlinAndroid).version(ProjectProperties.JETBRAINS_KOTLIN).apply(false)
    id (Plugins.jetbrainsKotlinJvm).version(ProjectProperties.JETBRAINS_KOTLIN).apply(false)
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
