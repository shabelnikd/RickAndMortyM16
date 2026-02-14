import com.android.tools.r8.internal.im

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
kotlin {
    jvmToolchain(17)

    dependencies {
        implementation(libs.koin.core)
        implementation(libs.bundles.kotlinx.coroutines)
        implementation(libs.androidx.paging.common)
        implementation(project(":core:network"))
    }
}

