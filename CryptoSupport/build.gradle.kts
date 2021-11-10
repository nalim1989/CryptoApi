import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
}

group = "me.milancop"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation ("com.github.mrmike:ok2curl:0.7.0")
    implementation ("org.java-websocket:Java-WebSocket:1.5.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useTestNG()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}