# Proposal: Migrate Android Navigation 2 to Jetpack Navigation 3

## Why

Jetpack Navigation 2 relies on imperative `NavController` state management, graph DSLs (`NavHost`), and string/type-safe destination matching attached to host fragments/activities. 

Jetpack Navigation 3 transitions navigation to a declarative, state-first paradigm where the back stack is a pure Kotlin list of `NavKey` instances (`SnapshotStateList<NavKey>`). This decoupled state-driven architecture drastically simplifies state preservation across process death, eliminates complex `NavController` test mocking, and allows pure Kotlin unit testing for navigation behavior.

## What Changes

- **Dependencies**: Upgrade and install Jetpack Navigation 3 artifacts (`navigation3-core`, `navigation3-compose`) in `libs.versions.toml` and `app/build.gradle.kts`.
- **Navigation Keys**: Refactor all routes (`LoginRoute`, `RegisterRoute`, `ProductDetailRoute`, etc.) into data classes/objects implementing `NavKey`.
- **Root Navigation**: Replace Nav 2 `NavController` / `NavHost` in `AppNavigation` with Nav 3 state list and `NavDisplay` with `entryProvider`.
- **Tab Navigation**: Replace `navController.navigate(...)` tab switching in `MainScreen` with tab state and multiple back stack list management.
- **Unit & UI Tests**: Rewrite all navigation unit tests (`src/test/`) and instrumented UI tests (`src/androidTest/`) to assert on `NavKey` state transitions and Nav 3 `NavDisplay` rendering.

## Capabilities

### New Capabilities
- `app-navigation`: Declarative Jetpack Navigation 3 flow management, handling authentication state transitions, nested tab navigation with independent back stacks, argument passing, and backstack restoration.

### Modified Capabilities
<!-- None: This project is migrating to OpenSpec declarative navigation -->

## Impact

- **Dependencies**: `libs.versions.toml` and `app/build.gradle.kts`.
- **Navigation Code**: `Routes.kt`, `AppNavigation.kt`, `MainNavGraph.kt`, `MainScreen.kt`.
- **Test Code**: All test suites in `app/src/test/java/com/example/composenav/` and `app/src/androidTest/java/com/example/composenav/`.
