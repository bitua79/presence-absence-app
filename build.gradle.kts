// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Gradle
        classpath("com.android.tools.build:gradle:7.0.4")

        // Kotlin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")

        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")

        // Navigation
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}
allprojects {
    repositories {
        google()
        maven(url = "https://jitpack.io")
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete("build")
}