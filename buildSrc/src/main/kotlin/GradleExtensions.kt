import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

// Plugins

fun PluginDependenciesSpec.androidApplication(): PluginDependencySpec = id("com.android.application")
fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec = id("com.android.library")
fun PluginDependenciesSpec.javaLibrary(): PluginDependencySpec = id("java-library")
fun PluginDependenciesSpec.kotlin(): PluginDependencySpec = id("kotlin")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec = id("kotlin-android")
fun PluginDependenciesSpec.kotlinAndroidExtensions(): PluginDependencySpec = id("kotlin-android-extensions")
fun PluginDependenciesSpec.kotlinKapt(): PluginDependencySpec = id("kotlin-kapt")

fun PluginDependenciesSpec.gradleVersions(): PluginDependencySpec = id("com.github.ben-manes.versions") version Versions.gradleVersionsPlugin