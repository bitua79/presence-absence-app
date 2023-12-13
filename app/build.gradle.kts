@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")

    // Parcelize
    id("org.jetbrains.kotlin.plugin.parcelize")

    // Hilt
    id("dagger.hilt.android.plugin")

    // Navigation
    id("androidx.navigation.safeargs.kotlin")

    // KSP
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}
android {
    compileSdk = 34

    defaultConfig {
        namespace = "com.application.presence_absence"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // UI
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.airbnb.android:lottie:5.2.0")

    // Glide
    ksp("com.github.bumptech.glide:ksp:4.14.2")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.2coffees1team:GlideToVectorYou:v2.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-work:1.1.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.1.0")
}