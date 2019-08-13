import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    gradleVersions()
}

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        Classpath.run {
            classpath(gradlePlugin)
            classpath(kotlinPlugin)
            classpath(gradleVersionsPlugin)
        }
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

//////////////////////////////////////////////////
// next task may be highlighted with errors
// to resolve it try to re-import dependencies
// into this script
//////////////////////////////////////////////////
tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea").any { qualifier ->
                    candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
                }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "plain"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

tasks {
    register("clean")
    "clean" {
        delete(rootProject.buildDir)
    }
}
