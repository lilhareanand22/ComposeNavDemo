# Gradle `verifyAll` Task Documentation

The `verifyAll` task is a custom Gradle task defined in the root [`build.gradle.kts`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/build.gradle.kts) that provides a single, unified verification pipeline for the **ComposeNavDemo** Android application.

It orchestrates compilation, unit & UI testing, code formatting enforcement, static code analysis, and Android linting sequentially to ensure code quality before pushing changes or running CI/CD builds.

---

## 🚀 Quick Start

Run the full verification pipeline with a single command:

```bash
# On Linux / macOS
./gradlew verifyAll

# On Windows Command Prompt / PowerShell
gradlew.bat verifyAll
```

---

## ⚙️ Implementation Details

The task is registered in the root [`build.gradle.kts`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/build.gradle.kts) under the `Verification` group:

```kotlin
// Define a custom task named 'verifyAll' that chains verification checks
tasks.register("verifyAll") {
    group = "Verification"
    description = "Runs compilation, unit tests, ktlint, detekt, and lint checks sequentially."

    // Define the execution order of verification tools
    dependsOn(
        ":app:assembleDebug",
        ":app:testDebugUnitTest",
        ":app:ktlintCheck",
        ":app:detekt",
        ":app:lintDebug"
    )

    // Log start message when pipeline begins
    doFirst {
        println("=== Starting Full Android Verification Pipeline via Gradle ===")
    }

    // Log success message when all tasks succeed
    doLast {
        println("=== All Verification Checks Passed Successfully! ===")
    }
}
```

---

## 📋 Task Pipeline Breakdown

The `verifyAll` task aggregates **5 key verification stages**:

| Stage | Task Name | Domain | Primary Purpose |
| :---: | :--- | :--- | :--- |
| **1** | `:app:assembleDebug` | Build & Compilation | Verifies code compilation and generates the debug APK. |
| **2** | `:app:testDebugUnitTest` | Testing & Coverage | Executes all local Unit & Robolectric Compose UI tests. |
| **3** | `:app:ktlintCheck` | Code Formatting | Enforces official Kotlin code style and formatting rules. |
| **4** | `:app:detekt` | Static Analysis | Detects code smells, complexity, and potential bugs. |
| **5** | `:app:lintDebug` | Android Platform Quality | Scans for Android-specific bugs, security issues, and performance warnings. |

---

### 1. `:app:assembleDebug`
* **Purpose**: Compiles all Kotlin source code, processes Android resources (`res/`), and packages the debug APK (`app-debug.apk`).
* **What it verifies**:
  - Kotlin syntax correctness and type safety.
  - Resource ID resolution (`R.*`) and Jetpack Compose compiler compatibility.
  - Library dependency graph resolution.
* **Failure Output**: Compiler errors with line numbers and file paths.

---

### 2. `:app:testDebugUnitTest`
* **Purpose**: Runs all 35 local unit, Compose UI behavior tests under Robolectric, Konsist architecture, and Konsist security tests.
* **What it verifies**:
  - Navigation flows (Auth Graph → Main Graph, nested tab switching).
  - Screen rendering, text input handling, and button callbacks.
  - Data class models (`Product`, `BottomNavItem`) and type-safe route serialization.
  - **Konsist Architecture Rules** ([`ArchitectureRulesTest.kt`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/src/test/java/com/example/composenav/architecture/ArchitectureRulesTest.kt)): Enforces clean architectural layer dependencies (`navigation`, `screens`, `ui`) and ViewModel/UseCase conventions.
  - **Konsist Security Rules** ([`SecurityRulesTest.kt`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/src/test/java/com/example/composenav/architecture/SecurityRulesTest.kt)): Prevents usage of insecure random generators (`java.util.Random`), raw hash digests (`MessageDigest`), and hardcoded secret keys.
* **Artifacts & Reports**:
  - Test Results: [`app/build/reports/tests/testDebugUnitTest/index.html`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/build/reports/tests/testDebugUnitTest/index.html)
  - JaCoCo Coverage Report: [`app/build/reports/jacoco/jacocoTestReport/html/index.html`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/build/reports/jacoco/jacocoTestReport/html/index.html)

---

### 3. `:app:ktlintCheck`
* **Purpose**: Scans all `.kt` and `.kts` files against official Kotlin style guidelines using the `org.jlleitschuh.gradle.ktlint` plugin.
* **What it verifies**:
  - Proper indentation, spacing, and bracket placement.
  - Elimination of unused imports and trailing whitespace.
  - Naming conventions for functions, variables, and files.
* **Auto-format Fix**: Run `./gradlew ktlintFormat` to automatically correct formatting violations.

---

### 4. `:app:detekt`
* **Purpose**: Runs static code analysis using Detekt based on rules configured in [`config/detekt/detekt.yml`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/config/detekt/detekt.yml).
* **What it verifies**:
  - Cyclomatic complexity and deeply nested logic.
  - Unused or dead code paths.
  - Potential performance bottlenecks, code smells, and security rule sets.
* **Reports**: [`app/build/reports/detekt/detekt.html`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/build/reports/detekt/detekt.html)

---

### 5. `:app:lintDebug`
* **Purpose**: Scans Android project resources, code, and manifest with the official Android Lint tool with strict `Security` issue escalation (`error.add("Security")` in [`app/build.gradle.kts`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/build.gradle.kts)).
* **What it verifies**:
  - `AndroidManifest.xml` and component security configurations (exported components, cleartext traffic).
  - Missing translations or un-localized strings.
  - API deprecations and SDK level incompatibilities.
  - Compose performance anti-patterns (e.g. unstable lambda captures).
* **Reports**: [`app/build/reports/lint-results-debug.html`](file:///C:/Users/Varsha/.gemini/antigravity/scratch/ComposeNavDemo/app/build/reports/lint-results-debug.html)

---

## 🔄 CI/CD & Git Hook Integration

### GitHub Actions Workflow Example

Add `verifyAll` to `.github/workflows/ci.yml`:

```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  verify:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Run Full Android Verification Pipeline
        run: ./gradlew verifyAll
```

### Git Pre-Push Hook

To automatically run verification locally before pushing to remote:

```bash
# Create .git/hooks/pre-push
echo "#!/bin/sh" > .git/hooks/pre-push
echo "./gradlew verifyAll" >> .git/hooks/pre-push
chmod +x .git/hooks/pre-push
```
