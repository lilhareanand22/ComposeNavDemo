# Design: Jetpack Navigation 3 Migration

## Context

The application currently uses Jetpack Navigation 2 with string/type-safe routes and `NavController`. See `proposal.md` for motivation.

## Goals / Non-Goals

**Goals:**
- Replace Nav 2 `NavController` with Nav 3 explicit state management (`NavKey` & `NavDisplay`).
- Retain exact existing app capabilities: Auth Flow (Login, Register), Main Flow (Home, Explore, Profile, Product Detail, Settings).
- Use Option A: Pure UI-layer state management (`rememberSaveableBackStack` or custom `TabNavigationStateHolder`) for tab backstacks.
- Rewrite all 28 local unit/UI tests (`src/test/`) and instrumented UI tests (`src/androidTest/`) to test Nav 3 state lists and composables.
- Pass full `./gradlew verifyAll` verification pipeline post-migration.

**Non-Goals:**
- Changing app visual UI design or color themes.
- Converting state to ViewModel-driven Flow unless requested.

## Decisions

1. **`NavKey` Interfaces**:
   - All destination routes in `Routes.kt` implement `NavKey` and retain `@Serializable` annotation for state restoration across process death.

2. **EntryProvider DSL**:
   - Use `entryProvider` DSL to map `NavKey` types to screen composables cleanly in `AppNavigation.kt` and `MainNavGraph.kt`.

3. **Multiple Tab Backstacks State Ownership**:
   - Option A: Maintain a map of active `NavKey` lists per tab in a state wrapper (`rememberSaveable`) in `MainScreen`.

4. **Test Assertion Model**:
   - Local unit tests verify state operations on `SnapshotStateList<NavKey>` directly.
   - Compose UI tests render `NavDisplay` and assert screen contents and callbacks.

## Risks / Trade-offs

- **[Risk]**: Deprecated Nav 2 testing helpers (`TestNavHostController`) no longer apply.
  - *Mitigation*: Replace with direct `NavKey` state list assertions and Compose test rules.
- **[Risk]**: State serialization across process death during testing under Robolectric.
  - *Mitigation*: Ensure all keys are annotated with `@Serializable` or `@Parcelize`.
