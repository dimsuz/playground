buildscript {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath(kotlin("gradle-plugin", version = "1.5.31"))
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.0.0-beta5")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
