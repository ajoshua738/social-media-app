buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}



plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
}



android {
    namespace = "com.example.socialmediaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.socialmediaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.code.ksoap2-android:ksoap2-android:3.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //room database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:2.6.1")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")





    //Lottie Animations
    implementation("com.airbnb.android:lottie:4.0.0")


    //Swipe Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")


   


    //Expandable text view
    implementation("io.github.glailton.expandabletextview:expandabletextview:1.0.4")



    //OTP
    implementation("io.github.chaosleung:pinview:1.4.4")


    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")


    //Media 3 exo player
    implementation("androidx.media3:media3-exoplayer:1.3.0")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.0")
    implementation("androidx.media3:media3-ui:1.3.0")



    //Sugar ORM
    implementation("com.github.satyan:sugar:1.5")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")


    //View Pager Circle indicator
    implementation("me.relex:circleindicator:2.1.6")


    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")



    //Image fullscreen
    implementation("com.github.stfalcon-studio:StfalconImageViewer:v1.0.1")



    //Volley
    implementation("com.android.volley:volley:1.2.1")


    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")

}


