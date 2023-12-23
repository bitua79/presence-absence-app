pluginManagement {
    repositories {
        val localProperties = java.util.Properties()
        localProperties.load(java.io.FileInputStream(file("local.properties")))

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

rootProject.name = "Presence-Absence"
include(":app")