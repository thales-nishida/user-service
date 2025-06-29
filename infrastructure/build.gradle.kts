/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.14/userguide/building_java_projects.html in the Gradle documentation.
 */

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    application
    java
}

application {
    mainClass.set("br.com.thalesnishida.user.service.infrastructure.Main")
}

group = "br.com.thalesnishida.user.service.infrastructure"
version = "1.0-SNAPSHOT"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(libs.guava)
    
    implementation("mysql:mysql-connector-java")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("com.h2database:h2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
        events("standardOut", "started", "passed", "skipped", "failed")
        showCauses = true
        showExceptions = true
        showStackTraces = true
    }

    afterSuite(KotlinClosure2<TestDescriptor, TestResult, Unit>({ desc, result ->
        if (desc.parent == null) { 
            println("----")
            println("Test result: ${result.resultType}")
            println("Test summary: ${result.testCount} tests, " +
                    "${result.successfulTestCount} succeeded, " +
                    "${result.failedTestCount} failed, " +
                    "${result.skippedTestCount} skipped")
        }
    }))
}

tasks.withType<Test> {
    outputs.upToDateWhen { false } }

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("application.jar")
    destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
}
