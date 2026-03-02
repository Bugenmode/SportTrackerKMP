# Sport Tracker

Кроссплатформенное мобильное приложение для отслеживания фитнес-активности, построенное на **Kotlin Multiplatform**.

## Скриншоты

<p align="center">
  <img src="screenshots/home_progress.jpg" width="300" alt="Главный экран — прогресс и статистика" />
  &nbsp;&nbsp;&nbsp;
  <img src="screenshots/weekly_activity.jpg" width="300" alt="Активность за неделю" />
</p>

## Возможности

- **Дневной прогресс** — круговые индикаторы для шагов, калорий, сна и активности
- **Статистика** — карточки с ключевыми метриками за день
- **Недельный график** — интерактивная визуализация шагов и калорий за неделю
- **Навигация по датам** — переключение между днями
- **Анимации** — плавные переходы и появление элементов

## Стек технологий

| Слой | Технологии |
|------|-----------|
| **Архитектура** | Clean Architecture, MVVM |
| **Shared** | Kotlin Multiplatform |
| **Android UI** | Jetpack Compose, Material 3 |
| **iOS UI** | SwiftUI |
| **Сеть** | Ktor Client |
| **DI** | Koin |
| **Сериализация** | Kotlinx Serialization |
| **Навигация** | Navigation Compose |
| **Асинхронность** | Kotlinx Coroutines |

## Структура проекта

```
SportTracker/
├── shared/                  # Общий KMM-модуль
│   ├── commonMain/          # Общий код (domain, data, utils)
│   ├── androidMain/         # Android-реализации (OkHttp)
│   └── iosMain/             # iOS-реализации (Darwin)
├── androidApp/              # Android-приложение
│   └── ui/
│       ├── screens/         # Экраны (Home, Workouts, Sleep, Goals)
│       ├── components/      # UI-компоненты
│       ├── theme/           # Тема и цвета
│       └── navigation/      # Навигация
└── iosApp/                  # iOS-приложение
```

## Требования

- Android: minSdk 24 (Android 7.0+)
- iOS: arm64 / x64 / simulator arm64
- Kotlin 2.0.0
- Gradle 8.8

## Сборка и запуск

### Android

```bash
./gradlew :androidApp:installDebug
```

### iOS

Откройте `iosApp/iosApp.xcodeproj` в Xcode и запустите проект.