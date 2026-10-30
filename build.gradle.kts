plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    id("org.owasp.dependencycheck") version "12.1.0" apply false
}

// Define a custom task named 'verifyAll' that chains your verification checks
tasks.register("verifyAll") {
    group = "Verification"
    description = "Runs compilation, unit tests, ktlint, detekt, lint, and OWASP dependency checks sequentially."

    // Define the execution order of your tools
    dependsOn(
        ":app:assembleDebug",
        ":app:testDebugUnitTest",
        ":app:ktlintCheck",
        ":app:detekt",
        ":app:lintDebug",
        ":app:dependencyCheckAnalyze"
    )

    // Optional: Print a message when the pipeline starts
    doFirst {
        println("=== Starting Full Android Verification Pipeline via Gradle ===")
    }

    // Optional: Print a success message if all tasks succeed
    doLast {
        println("=== All Verification Checks Passed Successfully! ===")
    }
}
