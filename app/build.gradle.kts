plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.quoteapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quoteapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation (libs.androidx.core.ktx.v180)
    implementation (libs.androidx.appcompat.v150)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.appcompat)
    implementation (libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}