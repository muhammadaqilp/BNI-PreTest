// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    kotlin(libs.plugins.jvm.get().pluginId).version(libs.versions.jvm)
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.pluginSerialization)
}