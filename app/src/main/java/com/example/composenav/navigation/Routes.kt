package com.example.composenav.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// ─── Auth Graph ───────────────────────────────────────────────
@Serializable object AuthGraph : NavKey

@Serializable object LoginRoute : NavKey

@Serializable object RegisterRoute : NavKey

// ─── Main Graph ───────────────────────────────────────────────
@Serializable object MainGraph : NavKey

// Bottom nav tabs
@Serializable object HomeRoute : NavKey

@Serializable object ExploreRoute : NavKey

@Serializable object ProfileRoute : NavKey

// Detail screen (with required argument)
@Serializable data class ProductDetailRoute(val productId: Int, val productName: String) : NavKey

// Settings (nested inside Profile tab)
@Serializable object SettingsRoute : NavKey
