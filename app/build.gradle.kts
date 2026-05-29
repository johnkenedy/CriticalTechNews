plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.makethiswork.criticaltechnews"
    compileSdk = 37

    defaultConfig {
        applicationId = "dev.makethiswork.criticaltechnews"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // API key is read from the user's gradle.properties as NEWS_API_KEY
        val newsApiKey = (project.findProperty("NEWS_API_KEY") as String?).orEmpty()
        buildConfigField("String", "NEWS_API_KEY", "\"$newsApiKey\"")
    }

    flavorDimensions += "source"
    productFlavors {
        create("bbc") {
            dimension = "source"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = "-bbc"
            buildConfigField("String", "SOURCE_ID", "\"bbc-news\"")
            buildConfigField("String", "SOURCE_NAME", "\"BBC News\"")
        }
        create("cnn") {
            dimension = "source"
            applicationIdSuffix = ".cnn"
            versionNameSuffix = "-cnn"
            buildConfigField("String", "SOURCE_ID", "\"cnn\"")
            buildConfigField("String", "SOURCE_NAME", "\"CNN\"")
        }
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
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    // DI
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Desugaring
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Images
    implementation(libs.coil.compose)

    // Bonus stories
    implementation(libs.androidx.biometric)
    implementation(libs.androidx.browser)

    // Unit tests
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockwebserver)

    // Instrumented tests
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
