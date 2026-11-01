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
include(":data-local")
include(":core:base")
include(":core:common")
include(":core:navigation")
include(":core:designsystem")
include(":feature:member")
include(":feature:admin")
include(":feature:attendance")
include(":feature:main")
