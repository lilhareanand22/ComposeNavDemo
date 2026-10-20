# Tasks

## 1. Dependencies & Configuration

- [x] 1.1 Update `gradle/libs.versions.toml` and `app/build.gradle.kts` to add Navigation 3 dependencies (`navigation3-core`, `navigation3-compose`) and verify gradle build succeeds via `./gradlew app:compileDebugKotlin`
- [x] 1.2 Convert `Routes.kt` routes to data classes/objects implementing `NavKey` and verify compilation

## 2. Navigation Implementation

- [x] 2.1 Refactor `AppNavigation.kt` to use Nav 3 `NavDisplay` and `entryProvider` for Auth and Main screen flows and verify compilation
- [x] 2.2 Refactor `MainNavGraph.kt` and `MainScreen.kt` to manage tab backstacks (`HomeKey`, `ExploreKey`, `ProfileKey`) and `NavDisplay` for main tab navigation
- [x] 2.3 Verify manual app launch and UI interactions

## 3. Unit & UI Test Rewrites

- [x] 3.1 Update `RoutesAndModelsTest.kt` for `NavKey` interfaces and verify unit test passes
- [x] 3.2 Update `AppNavigationTest.kt` and `MainNavGraphTest.kt` for Nav 3 state list testing
- [x] 3.3 Update screen UI tests (`LoginScreenTest`, `RegisterScreenTest`, `HomeScreenTest`, `MainScreenTest`, `ProfileScreenTest`, `SettingsScreenTest`, `ProductDetailScreenTest`) for Nav 3 composables
- [x] 3.4 Update instrumented tests (`AppNavigationInstrumentedTest.kt`) in `src/androidTest/` for Nav 3

## 4. Final Verification

- [x] 4.1 Run full `./gradlew verifyAll` task pipeline to confirm build, unit tests, ktlint, detekt, and lint checks pass cleanly
