plugins {
    id("module.android")
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.bundles.navigation)
}
