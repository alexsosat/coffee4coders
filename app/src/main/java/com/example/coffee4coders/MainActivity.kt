package com.example.coffee4coders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffee4coders.ui.components.CountryISO
import com.example.coffee4coders.ui.screens.CheckoutScreen
import com.example.coffee4coders.ui.screens.DetailsScreen
import com.example.coffee4coders.ui.screens.FeedScreen
import com.example.coffee4coders.ui.theme.Coffee4codersTheme
import com.example.coffee4coders.utilities.MockDataProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost()
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    Coffee4codersTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "feed"
            ) {
                composable(route = "feed") {
                    FeedScreen(navController = navController)
                }
                composable(route = "details/{productId}") { backStackEntry ->
                    val productIdString =
                        backStackEntry.arguments?.getString("productId") ?: "0"
                    val productId = productIdString.toInt()
                    val product = MockDataProvider.getProductById(productId)
                    DetailsScreen(
                        navController = navController,
                        product = product!!
                    )
                }
                composable(route = "checkout/{productId}") { backStackEntry ->
                    val productIdString =
                        backStackEntry.arguments?.getString("productId") ?: "0"
                    val productId = productIdString.toInt()
                    val product = MockDataProvider.getProductById(productId)
                    CheckoutScreen(
                        navController = navController,
                        product = product!!
                    )
                }
            }
        }
    }
}
