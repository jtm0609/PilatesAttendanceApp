plugins {
    id("module.feature")
}
android {
    namespace = "com.example.feature.main"
}
dependencies {
    implementation(project(":feature:member"))
    implementation(project(":feature:admin"))
    implementation(project(":feature:attendance"))
}