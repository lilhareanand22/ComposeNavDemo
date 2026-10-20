# Spec Delta: App Navigation

## Purpose

Provides declarative, state-driven Jetpack Navigation 3 flow management for screen navigation, authentication transitions, nested tab displays with independent back stacks, and navigation argument handling.

## ADDED Requirements

### Requirement: Navigation State Management
The system MUST represent the active navigation hierarchy using explicit Kotlin state lists (`SnapshotStateList<NavKey>`) rather than an imperative `NavController`.

#### Scenario: Backstack push and pop operations
- **WHEN** a navigation key is added to or removed from the back stack list
- **THEN** the `NavDisplay` updates the visible screen composable to match the active top key

### Requirement: Authentication Navigation Flow
The system MUST manage authentication transitions (Login and Register) and route successful authentication events directly into the main application navigation container.

#### Scenario: Navigating to Register from Login
- **WHEN** user selects the Register option on the Login screen
- **THEN** the `RegisterKey` is pushed onto the root back stack and the Register screen is displayed

#### Scenario: Returning to Login from Register
- **WHEN** user triggers back navigation from the Register screen
- **THEN** the `RegisterKey` is popped from the root back stack and the Login screen is restored

#### Scenario: Successful Login or Registration Transition
- **WHEN** user completes login or account registration successfully
- **THEN** the root back stack clears authentication keys and displays the Main application container

### Requirement: Nested Tab Navigation with Independent Backstacks
The system MUST support bottom navigation across multiple tabs (Home, Explore, Profile), maintaining independent navigation back stacks and scroll state for each tab.

#### Scenario: Tab switching preserves tab backstack history
- **WHEN** user switches between bottom navigation tabs
- **THEN** the active tab changes and the navigation history and scroll state of the newly selected tab is restored

#### Scenario: Deep navigation within a tab
- **WHEN** user selects a product on the Home screen or Settings on the Profile screen
- **THEN** the detail key (`ProductDetailKey` or `SettingsKey`) is added to that tab's back stack and the detail screen is displayed with passed arguments

### Requirement: Navigation State Testing
The system MUST support direct unit testing of navigation state transitions on pure Kotlin `NavKey` state lists without requiring `TestNavHostController` mocks.

#### Scenario: Asserting navigation key transitions
- **WHEN** navigation callbacks are invoked during testing
- **THEN** unit tests assert directly on the `NavKey` contents and size of the back stack list
