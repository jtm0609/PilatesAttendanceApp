plugins {
    id("module.feature")
}
android {
    namespace = "com.example.feature.registration"
}
dependencies{
    implementation(project(":feature:search"))
}