plugins {
    androidApplication()
    kotlinAndroid()
    kotlinAndroidExtensions()
}

android {
    compileSdkVersion(AndroidSrc.compileSdk)
    defaultConfig {
        applicationId = AndroidSrc.applicationId
        minSdkVersion(AndroidSrc.minSdk)
        targetSdkVersion(AndroidSrc.targetSdk)

        versionCode = AndroidSrc.versionCode
        versionName = "${AndroidSrc.versionName}-b-<${System.getenv("CI_BUILD_REF_NAME")}>"
        multiDexEnabled = true
    }

    /*signingConfigs {
        named(Flavors.release) {
            storeFile = file(RELEASE_STORE_FILE)
            storePassword = RELEASE_KEYSTORE_PASSWORD
            keyAlias = RELEASE_KEY_ALIAS_NAME
            keyPassword = RELEASE_KEY_ALIAS_PASSWORD
        }
    }*/

    buildTypes {
        named(Flavors.release) {
            //            signingConfig = signingConfigs.getByName(Flavors.release)
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        named(Flavors.debug) {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    val dimension ="newDim"
    flavorDimensions(dimension)
    productFlavors {
        create("default") {
            setDimension(dimension)
        }
    }
}

dependencies {
    Libraries.run {
        implementation(kotlinStdLib)
        implementation(appCompat)
        implementation(ktx)
        implementation(constraintlayout)
    }
}
