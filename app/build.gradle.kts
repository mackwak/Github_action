import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // Add Kapt plugin
    id("com.google.dagger.hilt.android") // Add Hilt plugin
}
val keystorePropertiesFile = rootProject.file("app/keystore.properties")
// Create a new Properties object
val keystoreProperties = Properties() // <-- 2. Declare the variable here

// Load the properties file if it exists
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.example.githubaction"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.githubaction"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        // The debug config is pre-defined by Android Studio
        getByName("debug") {
            // This is the default debug keystore
        }
        // You would create a 'release' config here for production,
        // loading properties from a secure file.
        // For example:
        create("release") {
             storeFile = file("./keystore.jks")
             storePassword = keystoreProperties.getProperty("storePassword")
             keyAlias = keystoreProperties.getProperty("keyAlias")
             keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        create("qa") {
            // This makes the 'qa' build debuggable
            isDebuggable = true
            applicationIdSuffix = ".qa"
            signingConfig = signingConfigs.getByName("debug")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("com.google.dagger:hilt-android:2.51.1") // Use the latest stable version
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    // For ViewModel injection in Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}