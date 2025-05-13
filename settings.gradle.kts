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
include(":feature:registration")
include(":feature:search")
include(":feature:manage")
include(":feature:setting")
include(":feature:attendance")
include(":feature:main")
