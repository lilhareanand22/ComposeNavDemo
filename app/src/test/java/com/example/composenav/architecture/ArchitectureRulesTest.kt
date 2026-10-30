package com.example.composenav.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class ArchitectureRulesTest {
    private val packageName = "com.example.composenav"

    @Test
    fun `navigation and ui layers depend on nothing`() {
        Konsist.scopeFromProduction().assertArchitecture {
            val navigation = Layer("Navigation", "$packageName.navigation..")
            val ui = Layer("UI", "$packageName.ui..")

            navigation.dependsOnNothing()
            ui.dependsOnNothing()
        }
    }

    @Test
    fun `viewmodels should reside in screens or presentation layer`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("ViewModel")
            .assertTrue {
                it.resideInPackage("$packageName.screens..") ||
                    it.resideInPackage("$packageName.presentation..")
            }
    }

    @Test
    fun `use cases should reside in domain layer and have single invoke method`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.resideInPackage("$packageName.domain..") &&
                    it.hasFunction { func -> func.name == "invoke" }
            }
    }
}
