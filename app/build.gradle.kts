plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sunnyweather.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sunnyweather.android"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            // 设置支持的SO库架构（开发者可以根据需要，选择一个或多个平台的so）
            abiFilters.add("armeabi-v7a")
            abiFilters.add("armeabi")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
            abiFilters.add("x86_64")


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
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    sourceSets {
        getByName("main") {
            jniLibs.setSrcDirs(listOf("libs"))
        }
    }
}

dependencies {

    val nav_version = "2.8.8"
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.accompanist.permissions)
    implementation("com.baidu.lbsyun:BaiduMapSDK_Map:7.6.4")
    implementation(libs.baidumapsdk.search)
    implementation(libs.baidumapsdk.location)
    implementation(files("libs/AMap2DMap_6.0.0_AMapSearch_9.7.4_AMapLocation_6.4.9_20241226.jar"))
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.30.1")
    implementation(libs.androidx.core.ktx)
    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation ("com.google.code.gson:gson:2.12.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}