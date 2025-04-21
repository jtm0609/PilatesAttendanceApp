pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "cmong_pilates_attendance_project"
include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":data-local")
include(":designsystem")
