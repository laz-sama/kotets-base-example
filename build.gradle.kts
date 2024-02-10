val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotest_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml:2.3.8")

    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
