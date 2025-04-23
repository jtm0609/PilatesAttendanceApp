plugins {
    id("module.android")
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.bundles.navigation)
}
