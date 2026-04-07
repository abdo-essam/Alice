# ProGuard rules for Compose Multiplatform when isMinifyEnabled = true

# Ktor & Coil rules needed because of reflection and ServiceLoader usage inside KMP Network libraries
-keep class io.ktor.** { *; }
-dontwarn java.lang.management.**
-dontwarn io.ktor.**
-keep class coil3.** { *; }

# Keep Coroutines & Serialization
-keep class kotlinx.coroutines.** { *; }
-keep class kotlinx.serialization.** { *; }

# Keep App Data Models to prevent serialization from breaking
-keep class com.ae.alice.** { *; }
