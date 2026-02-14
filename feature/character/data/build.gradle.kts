plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvmToolchain(17)

    dependencies {
        implementation(libs.koin.core)
        implementation(libs.bundles.kotlinx.coroutines)
        implementation(libs.androidx.paging.common)
        implementation(project(":core:network"))
        implementation(project(":feature:character:domain"))
    }
}

