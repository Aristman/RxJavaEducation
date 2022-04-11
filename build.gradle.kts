import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "ru.marslab"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")

    // RxJava 3
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")

    implementation ("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}