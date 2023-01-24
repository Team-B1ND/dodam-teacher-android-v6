pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}
rootProject.name = "Dodam_Teacher_V6"
include(":presentation")
include(":data")
include(":domain")
include(":di")
include(":local")
include(":remote")
