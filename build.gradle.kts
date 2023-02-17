plugins {
    kotlin("jvm") version "1.8.0"
}

group = "builders.volans"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.kyori:adventure-text-minimessage:4.12.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}