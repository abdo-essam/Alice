# Alice - Car Brands App

A Kotlin Multiplatform application for exploring car brands and models, built with Clean Architecture and modern Android/iOS development practices.

## 📱 Features

- **Browse Car Brands** - Explore manufacturers with logos, country info, and model counts
- **View Models** - Detailed model information with specs, pricing, and images
- **Search & Filter** - Find cars by brand, category, engine type, and more
- **Favorites** - Save your favorite models for quick access
- **Dark/Light Theme** - Full theme support with system preference detection
- **Localization** - English and Arabic (RTL) support

---

## 🏗 Project Architecture

```
alice/
├── composeApp/                    # Main app entry point
│   ├── src/commonMain/           # Shared app code
│   └── src/androidMain/          # Android-specific code
│
├── core/                          # Core modules
│   ├── designsystem/             # Colors, typography, dimensions, components
│   ├── common/                   # Base classes, utilities, extensions
│   ├── network/                  # Ktor HTTP client, API configuration
│   ├── data/                     # Repositories, DTOs, mappers, DataStore
│   ├── domain/                   # Entities, use cases, repository interfaces
│   └── ui/                       # Shared UI utilities, navigation
│
├── feature/                       # Feature modules
│   ├── home/                     # Brands list screen
│   ├── search/                   # Search functionality
│   └── favorites/                # Favorites management
│
├── iosApp/                        # iOS app entry point
└── gradle/                        # Gradle configuration
```

---

## 🧱 Module Dependencies

```
┌─────────────────────────────────────────────────────────────┐
│                       composeApp                            │
│  (Android/iOS entry, Koin setup, Navigation)                │
└───────────────────────────┬─────────────────────────────────┘
                            │
        ┌───────────────────┼───────────────────┐
        ▼                   ▼                   ▼
┌───────────────┐   ┌───────────────┐   ┌───────────────┐
│ feature:home  │   │feature:search │   │feature:favs   │
└───────┬───────┘   └───────┬───────┘   └───────┬───────┘
        │                   │                   │
        └───────────────────┼───────────────────┘
                            │
        ┌───────────────────┼───────────────────┐
        ▼                   ▼                   ▼
┌───────────────┐   ┌───────────────┐   ┌───────────────┐
│   core:ui     │   │ core:domain   │   │core:designsys │
└───────┬───────┘   └───────┬───────┘   └───────────────┘
        │                   │
        │           ┌───────┴───────┐
        │           ▼               ▼
        │   ┌───────────────┐   ┌───────────────┐
        │   │  core:data    │   │ core:common   │
        │   └───────┬───────┘   └───────────────┘
        │           │
        │           ▼
        │   ┌───────────────┐
        └──▶│ core:network  │
            └───────────────┘
```

---

## 🛠 Technology Stack

| Category | Technology |
|----------|------------|
| **Language** | Kotlin 2.3.0 |
| **UI Framework** | Compose Multiplatform 1.10.0 |
| **Architecture** | Clean Architecture + MVI |
| **Networking** | Ktor 3.1.0 |
| **DI** | Koin 4.0.0 |
| **Navigation** | Compose Navigation 2.9.6 |
| **Image Loading** | Coil 3.1.0 |
| **Local Storage** | DataStore 1.1.1 |
| **Serialization** | KotlinX Serialization 1.8.0 |

---

## 🎨 Design System

### Colors
Centralized in `AliceColors.kt` - supports light/dark themes with semantic naming.

### Typography
Defined in `AliceTypography.kt` - follows Material3 type scale.

### Dimensions
All spacing and sizes in `AliceDimensions.kt` - uses 4dp grid system.

### Components
Reusable components in `core:designsystem/components/`:
- `AliceButtons.kt` - Primary, Outlined, Text buttons
- `AliceCards.kt` - Card and Surface containers
- `AliceInputFields.kt` - TextField and SearchField
- `ShimmerEffect.kt` - Loading skeleton animations

---

## 📍 Key Patterns

### MVI (Model-View-Intent)
```kotlin
class MyViewModel : BaseViewModel<MyState, MyIntent, MyEffect>(MyState()) {
    override fun handleIntent(intent: MyIntent) {
        // Handle user actions
    }
}
```

### Safe API Calls
```kotlin
suspend fun getData(): Result<Data> = safeApiCall {
    apiService.fetchData()
}
```

### Repository Pattern
- Interfaces in `core:domain`
- Implementations in `core:data`
- DI wiring via Koin modules

---

## 🌐 Localization

| Language | File |
|----------|------|
| English | `values/strings.xml` |
| Arabic (RTL) | `values-ar/strings.xml` |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Xcode 15+ (for iOS)
- JDK 11+

### Build Android
```bash
./gradlew :composeApp:assembleDebug
```

### Build iOS
Open `iosApp/iosApp.xcodeproj` in Xcode and run.

### Run Tests
```bash
./gradlew test
```

---

## 📋 SOLID Principles Applied

| Principle | Implementation |
|-----------|---------------|
| **Single Responsibility** | Each class/module has one purpose |
| **Open/Closed** | Interfaces allow extension without modification |
| **Liskov Substitution** | Repository interfaces work with any implementation |
| **Interface Segregation** | Small, focused interfaces |
| **Dependency Inversion** | Domain doesn't depend on data/network |

---

## 📄 License

MIT License - see LICENSE file for details.