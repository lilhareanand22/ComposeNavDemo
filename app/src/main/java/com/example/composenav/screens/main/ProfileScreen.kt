package com.example.composenav.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onGoToSettings: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("👤 Profile") },
                actions = {
                    IconButton(onClick = onGoToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(24.dp))
            Text("👤", fontSize = 80.sp)
            Spacer(Modifier.height(16.dp))
            Text("John Doe", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("john@example.com", color = MaterialTheme.colorScheme.outline)
            Spacer(Modifier.height(8.dp))
            Text(
                "Main Graph → ProfileRoute",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Spacer(Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("💡 Tips:", style = MaterialTheme.typography.titleSmall)
                    Spacer(Modifier.height(8.dp))
                    Text("• Tap ⚙️ Settings icon in the top bar")
                    Text("• Demonstrates nested navigation from Profile")
                    Text("• Uses navController.navigate(SettingsRoute)")
                }
            }
        }
    }
}
