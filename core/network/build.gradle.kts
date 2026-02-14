plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
kotlin {
    jvmToolchain(17)

    dependencies {
        api(libs.bundles.ktor)
        implementation(libs.koin.core)
        implementation(libs.bundles.kotlinx.coroutines)
    }
}

