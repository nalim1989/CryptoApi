import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
}

group = "me.milancop"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":KrakenApi"))
    api(project(":CryptoSupport"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useTestNG()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}