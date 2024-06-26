buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath(libs.kotlin.gradle.plugin)
        // Include other classpath dependencies as needed
    }
}

allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

