# pokedex-circuit
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.10-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2024.3.1%20%28Meerkat%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-26-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-35-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

## Features
|목록 화면|즐겨찾기 화면|
|:-----:|:-----:|
|<img width="240" src="https://github.com/user-attachments/assets/a4063504-25b1-4347-9862-288aaa17cd20">|<img width="240" src="https://github.com/user-attachments/assets/12e89854-b62c-4743-a2cf-f224bba6f52a">|

|즐겨찾기 저장|즐겨찾기 삭제|
|:-----:|:-----:|
|<img width="240" src="https://github.com/user-attachments/assets/5c3bf491-a2e6-4cfc-acd5-d44f58de569f">|<img width="240" src="https://github.com/user-attachments/assets/996828c2-b973-4b23-a816-6e22706848f9">|

## Development

### Required

- IDE : Android Studio 최신 버전 (Download)
- JDK : Java 17을 실행할 수 있는 JDK
    - (권장) Android Studio 설치 시 Embeded 된 JDK (Open JDK)
    - Java 17을 사용하는 JDK (Open JDK, AdoptOpenJDK, GraalVM)     
- Kotlin Language : 2.1.20

### Language

- Kotlin

### Libraries

- AndroidX
  - Activity Compose
  - Core
  - Room
  - StartUp
  - Splash

- Kotlin Libraries (Coroutine, Serialization, Immutable Collection)
- Compose
  - Material3

- Dagger Hilt
- Retrofit, OkHttp
- Timber
- [Circuit](https://github.com/slackhq/circuit)
- [Compose-Stable-Marker](https://github.com/skydoves/compose-stable-marker)
- [Landscapist](https://github.com/skydoves/landscapist), Coil-Compose
- [ComposeExtensions](https://github.com/taehwandev/ComposeExtensions)
- [compose-effects](https://github.com/skydoves/compose-effects)

#### Test & Code analysis

- Ktlint
- Detekt

#### Gradle Dependency

- Gradle Version Catalog

## Architecture
Google Recommend Architecture based on [Now in Android](https://github.com/android/nowinandroid)

## Module

## Package Structure
```
├── app
│   └── application
├── build-logic
├── core
│   ├── common
│   ├── data
│   ├── database
│   ├── designsystem
│   ├── model
│   └── network
├── feature
│   ├── favorites
│   ├── favorites-detail
│   ├── list
│   ├── list-detail
│   └── main
└── gradle
    └── libs.versions.toml
```
<br/>
