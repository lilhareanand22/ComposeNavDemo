package com.example.composenav.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var notifications by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚙️ Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                    .padding(16.dp),
        ) {
            Text(
                "Main Graph → SettingsRoute",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Spacer(Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(8.dp)) {
                    ListItem(
                        headlineContent = { Text("Push Notifications") },
                        supportingContent = { Text("Receive app notifications") },
                        trailingContent = {
                            Switch(checked = notifications, onCheckedChange = { notifications = it })
                        },
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Dark Mode") },
                        supportingContent = { Text("Enable dark theme") },
                        trailingContent = {
                            Switch(checked = darkMode, onCheckedChange = { darkMode = it })
                        },
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("💡 Navigation Concept:", style = MaterialTheme.typography.titleSmall)
                    Spacer(Modifier.height(8.dp))
                    Text("This screen was reached via navigate(SettingsRoute) from ProfileScreen.")
                    Text("Press Back (↑) to return via popBackStack().")
                }
            }
        }
    }
}
