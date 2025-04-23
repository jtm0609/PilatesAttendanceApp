plugins {
    id("module.feature")
}
android {
    namespace = "com.example.feature.main"
}
dependencies {
    implementation(project(":feature:registration"))
    implementation(project(":feature:search"))
    implementation(project(":feature:manage"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:attendance"))
}