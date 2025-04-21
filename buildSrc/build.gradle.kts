plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

// 중복 파일 처리 전략 설정
tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

// Kotlin 플러그인 버전을 가져옴
val kotlinVersion = "2.0.0"

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin)
    implementation(libs.kotlin.compiler.embeddable)
    implementation(libs.hilt.gradle)
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
}