package com.example.comparamercado.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comparamercado.data.SessionManager
import com.example.comparamercado.ui.MainScren
import com.example.comparamercado.ui.features.login.LoginScreen
import com.example.comparamercado.ui.features.login.RegisterScreen
import com.example.comparamercado.ui.features.profile.MyProductScreeen
import com.example.comparamercado.ui.features.profile.ProfileScreen
import com.example.comparamercado.ui.features.profile.SettingsScreen
import com.example.comparamercado.ui.features.search.SearchScreen
import com.example.comparamercado.ui.features.shopping.ShoppingListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val context = LocalContext.current
    val sessionManager = remember {
        SessionManager(context)
    }
    val rotaInicial = if (sessionManager.getEmailUtilizador() != null) "Search" else "Login"

    NavHost(
        navController = navController,
        startDestination = rotaInicial
    ) {
        composable(route = "Login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(route = "Search"){
                        popUpTo(route = "Login") {
                            inclusive = true
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(route = "Register")
                }
            )
        }

        composable(route = "Register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(route = "Login") {
                        popUpTo(route = "Register") {
                            inclusive = true
                        }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "Search") {
            MainScren(
                rootNavController = navController
            )
        }

        composable(route = "Shopping") {

        }

        composable(route = "MyProducts") {
            MyProductScreeen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "Settings") {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "Profile") {
            ProfileScreen(
                onLogoutClick = {
                    navController.navigate(route = "Login") {
                        popUpTo(0)
                    }
                },
                onMyProductsClick = {
                    navController.navigate(route = "MyProducts")
                },
                onSettingsClick = {
                    navController.navigate(route = "Settings")
                }
            )
        }
    }
}