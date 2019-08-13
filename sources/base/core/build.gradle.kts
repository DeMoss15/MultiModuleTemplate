plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinAndroidExtensions()
    kotlinKapt()
}

android {
    compileSdkVersion(AndroidSrc.compileSdk)

    defaultConfig {
        minSdkVersion(AndroidSrc.minSdk)
        targetSdkVersion(AndroidSrc.targetSdk)
        versionCode = AndroidSrc.versionCode
        versionName = AndroidSrc.versionName
    }
}

dependencies {
    Libraries.run {
        // Kotlin
        implementation(kotlinReflection)

        // Coroutines
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)

        // Android UI
        implementation(ktx)
        implementation(appCompat)
        implementation(constraintlayout)
        implementation(recyclerView)
        implementation(material)

        // ViewModel and LiveData
        kapt(lifecycleCompiler)
        implementation(lifecycleExtensions)
        implementation(lifecycleViewModelKtx)

        // Glide
        implementation(glide)
        kapt(glideCompiler)
    }
}
