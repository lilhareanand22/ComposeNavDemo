package com.example.composenav.unit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation3.runtime.NavKey
import com.example.composenav.navigation.AuthGraph
import com.example.composenav.navigation.BottomNavItem
import com.example.composenav.navigation.ExploreRoute
import com.example.composenav.navigation.HomeRoute
import com.example.composenav.navigation.LoginRoute
import com.example.composenav.navigation.MainGraph
import com.example.composenav.navigation.ProductDetailRoute
import com.example.composenav.navigation.ProfileRoute
import com.example.composenav.navigation.RegisterRoute
import com.example.composenav.navigation.SettingsRoute
import com.example.composenav.navigation.bottomNavItems
import com.example.composenav.screens.main.Product
import com.example.composenav.screens.main.sampleProducts
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RoutesAndModelsTest {
    @Test
    fun testRoutesInstantiation() {
        assertNotNull(AuthGraph)
        assertNotNull(LoginRoute)
        assertNotNull(RegisterRoute)
        assertNotNull(MainGraph)
        assertNotNull(HomeRoute)
        assertNotNull(ExploreRoute)
        assertNotNull(ProfileRoute)
        assertNotNull(SettingsRoute)

        assertTrue(AuthGraph is NavKey)
        assertTrue(LoginRoute is NavKey)
        assertTrue(RegisterRoute is NavKey)
        assertTrue(MainGraph is NavKey)
        assertTrue(HomeRoute is NavKey)
        assertTrue(ExploreRoute is NavKey)
        assertTrue(ProfileRoute is NavKey)
        assertTrue(SettingsRoute is NavKey)
    }

    @Test
    fun testProductDetailRoute() {
        val route = ProductDetailRoute(productId = 42, productName = "Kotlin Book")
        assertEquals(42, route.productId)
        assertEquals("Kotlin Book", route.productName)
        assertTrue(route is NavKey)

        val copyRoute = route.copy(productId = 99)
        assertEquals(99, copyRoute.productId)

        assertEquals("ProductDetailRoute(productId=42, productName=Kotlin Book)", route.toString())
    }

    @Test
    fun testBottomNavItem() {
        val item = BottomNavItem(label = "Home", icon = Icons.Default.Home, route = HomeRoute)
        assertEquals("Home", item.label)
        assertEquals(Icons.Default.Home, item.icon)
        assertEquals(HomeRoute, item.route)

        val itemCopy = item.copy(label = "Custom Home")
        assertEquals("Custom Home", itemCopy.label)

        assertEquals(3, bottomNavItems.size)
        assertEquals("Home", bottomNavItems[0].label)
        assertEquals("Explore", bottomNavItems[1].label)
        assertEquals("Profile", bottomNavItems[2].label)
    }

    @Test
    fun testProductAndSampleData() {
        val product = Product(id = 1, name = "Test Name", description = "Test Desc", emoji = "⭐")
        assertEquals(1, product.id)
        assertEquals("Test Name", product.name)
        assertEquals("Test Desc", product.description)
        assertEquals("⭐", product.emoji)

        assertTrue(sampleProducts.isNotEmpty())
        assertEquals(5, sampleProducts.size)
        assertEquals(1, sampleProducts[0].id)
        assertEquals("Kotlin Book", sampleProducts[0].name)
    }
}
