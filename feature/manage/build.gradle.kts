plugins {
    id("module.feature")
}
android {
    namespace = "com.example.feature.manage"
}
dependencies{
    implementation(project(":feature:search"))
}