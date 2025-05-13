plugins {
    id("module.android")
    id("module.compose")
}

android {
    namespace = "com.example.designsystem"
}


dependencies {
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.hilt.navigation.compose)
}