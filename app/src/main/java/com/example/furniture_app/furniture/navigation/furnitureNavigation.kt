package com.example.furniture_app.furniture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furniture_app.furniture.Screens.CheckOutScreen
import com.example.furniture_app.furniture.Screens.ProductDetailScreen
import com.example.furniture_app.furniture.Screens.homeScreen

@Composable
fun FurnitureNavigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = home) {
        composable(home) {
            homeScreen(navHostController)
        }
        composable(productDetail) {
            ProductDetailScreen(navHostController)
        }
        composable(checkOut) {
            CheckOutScreen(navHostController)
        }
    }
}
const val home = "homeScreen"
const val productDetail = "ProductDetailScreen"
const val checkOut = "CheckoutScreen"