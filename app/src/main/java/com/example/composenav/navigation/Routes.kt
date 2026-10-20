package com.example.composenav.navigation

import kotlinx.serialization.Serializable

// ─── Auth Graph ───────────────────────────────────────────────
@Serializable object AuthGraph
@Serializable object LoginRoute
@Serializable object RegisterRoute

// ─── Main Graph ───────────────────────────────────────────────
@Serializable object MainGraph

// Bottom nav tabs
@Serializable object HomeRoute
@Serializable object ExploreRoute
@Serializable object ProfileRoute

// Detail screen (with required argument)
@Serializable data class ProductDetailRoute(val productId: Int, val productName: String)

// Settings (nested inside Profile tab)
@Serializable object SettingsRoute
