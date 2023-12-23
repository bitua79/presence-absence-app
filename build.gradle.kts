// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}
buildscript {
    repositories {
        val localProperties = java.util.Properties()
        localProperties.load(java.io.FileInputStream(rootProject.file("local.properties")))

        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = localProperties["nexus.username"] as? String
                password = localProperties["nexus.password"] as? String
            }
        }
        maven { url = uri("https://jitpack.io") }

    }
    dependencies {
        // Gradle
        classpath("com.android.tools.build:gradle:8.0.2")

        // Kotlin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")

        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")

        // Navigation
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}
allprojects {

    repositories {
        val localProperties = java.util.Properties()
        localProperties.load(java.io.FileInputStream(rootProject.file("local.properties")))

        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = localProperties["nexus.username"] as? String
                password = localProperties["nexus.password"] as? String
            }
        }
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean").configure {
    delete("build")
}