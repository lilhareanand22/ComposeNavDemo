package com.example.composenav.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class SecurityRulesTest {
    @Test
    fun `no production file should import java util Random`() {
        Konsist.scopeFromProduction()
            .files
            .assertTrue { file ->
                file.imports.none { it.name == "java.util.Random" }
            }
    }

    @Test
    fun `no production file should import insecure hash algorithms or raw message digest`() {
        Konsist.scopeFromProduction()
            .files
            .assertTrue { file ->
                file.imports.none { import ->
                    import.name.contains("MessageDigest")
                }
            }
    }

    @Test
    fun `no production property should have hardcoded password or secret names with literal values`() {
        Konsist.scopeFromProduction()
            .classes()
            .assertTrue { clazz ->
                clazz.properties().none { property ->
                    val name = property.name.lowercase()
                    (name.contains("password") || name.contains("secretkey")) &&
                        property.text.contains("=")
                }
            }
    }
}
