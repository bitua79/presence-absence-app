// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = "bita.karvizi"
                password = "5'&RER>>v^Xsv7?"
            }
        }
    }
    dependencies {
        // Gradle
        classpath("com.android.tools.build:gradle:8.0.2")

        // Kotlin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")

        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")

        // Navigation
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}
allprojects {
    repositories {
        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = "bita.karvizi"
                password = "5'&RER>>v^Xsv7?"
            }
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}