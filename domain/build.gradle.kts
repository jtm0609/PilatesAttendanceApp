plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-kapt")
}



dependencies {
    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //inject
    implementation("javax.inject:javax.inject:1")

    //serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}