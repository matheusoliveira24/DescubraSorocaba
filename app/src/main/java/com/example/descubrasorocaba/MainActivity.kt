package com.example.descubrasorocaba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DescubraSorocabaApp()
        }
    }
}

@Composable
fun DescubraSorocabaApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Descubra Sorocaba") }) }
    ) { padding ->
        NavHost(navController, startDestination = "home", modifier = Modifier.padding(padding)) {
            composable("home") { CategoryScreen(navController) }
            composable("cafes") { RecommendationScreen("Cafeterias", listOf("Café da Vila", "Benedito Café", "Café Vovó Lúcia")) }
        }
    }
}

@Composable
fun CategoryScreen(navController: androidx.navigation.NavHostController) {
    val categories = listOf("Cafeterias" to "cafes")
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(categories) { (name, route) ->
            Button(
                onClick = { navController.navigate(route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(name)
            }
        }
    }
}

@Composable
fun RecommendationScreen(title: String, items: List<String>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(items) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(item, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}