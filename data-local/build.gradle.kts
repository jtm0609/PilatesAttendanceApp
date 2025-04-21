plugins {
    id("module.android")
}

android {
    namespace = "com.example.local"
}

dependencies {
    implementation(project(":data"))

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    kapt(libs.androidx.room.compiler)
}