package com.example.composenav.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Product(val id: Int, val name: String, val description: String, val emoji: String)

val sampleProducts =
    listOf(
        Product(1, "Kotlin Book", "Learn Kotlin from scratch", "📘"),
        Product(2, "Compose UI Kit", "Beautiful UI components", "🎨"),
        Product(3, "Android Studio", "The official Android IDE", "🛠️"),
        Product(4, "Jetpack Navigation", "Navigate between screens", "🧭"),
        Product(5, "ViewModel", "Lifecycle-aware state holder", "🧠"),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onProductClick: (Int, String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🏠 Home") })
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                "Main Graph → Home Route",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Text(
                "Tap a product to navigate with arguments →",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                items(sampleProducts) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product.id, product.name) })
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(product.emoji, fontSize = 32.sp)
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text(product.description, style = MaterialTheme.typography.bodySmall)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}
