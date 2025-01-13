import com.vanniktech.maven.publish.AndroidSingleVariantLibrary

plugins {
    autowire(libs.plugins.android.library)
    autowire(libs.plugins.kotlin.android)
    autowire(libs.plugins.maven.publish)
}

group = property.project.groupName
version = property.project.yukihookapi.core.version

android {
    namespace = property.project.groupName
    compileSdk = property.project.android.compileSdk

    defaultConfig {
        minSdk = property.project.android.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
    lint { checkReleaseBuilds = false }
}

dependencies {
    compileOnly(de.robv.android.xposed.api)
    compileOnly(projects.yukihookapiStub)
    implementation(com.github.tiann.freeReflection)
    implementation(androidx.core.core.ktx)
    implementation(androidx.appcompat.appcompat)
    implementation(androidx.preference.preference.ktx)
}

mavenPublishing {
    configure(AndroidSingleVariantLibrary(publishJavadocJar = false))
    coordinates(
        groupId = group.toString(),
        artifactId = property.project.yukihookapi.core.moduleName,
        version = version.toString()
    )
}