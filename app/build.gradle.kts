plugins {
  id("com.android.application")
  id("androidx.navigation.safeargs")
//  id ("realm-android")
}


android {
  namespace = "com.example.shrimpfarm"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.shrimpfarm"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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


  buildFeatures {
    viewBinding = true
  }

  packagingOptions {
    exclude ("META-INF/native-image/org.mongodb/bson/native-image.properties")
  }
}

//realm {
//  syncEnabled = true
//}

dependencies {
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.10.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
  implementation("androidx.navigation:navigation-fragment:2.7.5")
  implementation("androidx.navigation:navigation-ui:2.7.5")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  implementation("org.mongodb:mongodb-driver-sync:4.11.1") // use the latest version
  implementation("com.android.volley:volley-cronet:1.2.1")
  implementation("androidx.recyclerview:recyclerview:1.3.2")
  implementation("androidx.cardview:cardview:1.0.0")
  implementation("com.squareup.okhttp3:okhttp:4.9.3")
  implementation("io.realm:realm-android-library:10.17.0") // Use the latest version
}