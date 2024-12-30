plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //rxjava
    implementation("io.reactivex.rxjava2:rxjava:2.2.17")

    //inject
    implementation("javax.inject:javax.inject:1")

    //serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}