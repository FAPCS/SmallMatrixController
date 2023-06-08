import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    application

    id("com.github.johnrengelman.shadow") version "8.1.0"
}

group = "me.fapcs"
version = "0.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api("me.fapcs:Shared:0.3.0")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        dependsOn("distTar", "distZip", "startScripts")

        archiveClassifier.set("")
    }
}

application {
    mainClass.set("me.fapcs.small_matrix_controller.SmallMatrixController")
}