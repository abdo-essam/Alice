# ProGuard rules for Compose Multiplatform when isMinifyEnabled = true

# Ktor & Coil rules needed because of reflection and ServiceLoader usage inside KMP Network libraries
-keep class io.ktor.** { *; }
-dontwarn java.lang.management.**
-dontwarn io.ktor.**
-keep class coil3.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Ktor ServiceLoader rules
-keepnames class * implements io.ktor.client.engine.HttpClientEngineContainer
-keepclassmembers class * implements io.ktor.client.engine.HttpClientEngineContainer {
    <init>(...);
}

# Coil ServiceLoader rules
-keepnames class * implements coil3.network.NetworkFetcher$Factory
-keepclassmembers class * implements coil3.network.NetworkFetcher$Factory {
    <init>(...);
}

# Keep Coroutines & Serialization
-keep class kotlinx.coroutines.** { *; }
-keep class kotlinx.serialization.** { *; }

# Keep App Data Models to prevent serialization from breaking
-keep class com.ae.alice.** { *; }
