package com.kgurgul.cpuinfo.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.0"

    const val bus = "org.greenrobot:eventbus:3.1.1"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val junit = "junit:junit:4.13"

    const val robolectric = "org.robolectric:robolectric:4.3.1"

    const val mockitoCore = "org.mockito:mockito-core:3.2.4"

    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"

    object Kotlin {
        private const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Coroutines {
        private const val version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.0"
        const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.4"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val preference = "androidx.preference:preference:1.1.1"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val multiDex = "androidx.multidex:multidex:2.0.1"

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Navigation {
            private const val version = "2.2.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"

            object Espresso {
                private const val version = "3.2.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
            }
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.2.0-beta01"
        const val gson = "com.google.code.gson:gson:2.8.5"
    }

    object Rx {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.17"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Dagger {
        private const val version = "2.28"
        const val dagger = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Airbnb {
        private const val version = "4.0.0-beta4"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
        const val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
    }
}