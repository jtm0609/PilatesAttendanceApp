plugins {
    id("module.feature")
}
android {
    namespace = "com.example.feature.setting"
}
dependencies{
    implementation(project(":feature:search"))
}