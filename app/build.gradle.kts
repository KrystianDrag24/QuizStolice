plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.quizstolice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quizstolice"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Podstawowe zależności aplikacji
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    // Testowanie
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room - Biblioteka do zarządzania bazą danych
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0") // Dla Javy


    // Dodatkowe zależności, jeśli używasz Kotlin:
    // kapt("androidx.room:room-compiler:2.5.0") // Używane zamiast annotationProcessor dla Kotlina
}
