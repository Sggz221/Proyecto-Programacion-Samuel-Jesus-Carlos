plugins {

    kotlin("jvm") version "1.9.20"
    application
    // documentation
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.lighthousegames:logging:1.5.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveFileName.set("Oliver.jar")
}

kotlin {
    jvmToolchain(21)
}