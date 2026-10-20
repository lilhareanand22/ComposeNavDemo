# Agent Workflow & Verification Guidelines

## 1. Post-Task Verification Rule
- Whenever you finish implementing a code change or feature, you **MUST** immediately run the verification task via the terminal:
    - macOS/Linux: `./gradlew verifyAll`
    - Windows: `gradlew.bat verifyAll`

## 2. Strict Error Handling & Notification Policy
- **DO NOT auto-fix** any failures or issues detected by `verifyAll` (such as compilation errors, unit test failures, ktlint style violations, or detekt warnings).
- **Notify First:** Output a clear summary of what failed and the exact error log/trace.
- **Wait for Confirmation:** Stop execution and wait explicitly for user review and confirmation before proposing or making any corrective changes.