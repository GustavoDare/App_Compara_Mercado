package com.example.comparamercado.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comparamercado.ui.components.BottomNavigationBar
import com.example.comparamercado.ui.features.inform.InformScreen
import com.example.comparamercado.ui.features.profile.ProfileScreen
import com.example.comparamercado.ui.features.search.SearchScreen
import com.example.comparamercado.ui.features.shopping.ShoppingListScreen
import com.example.comparamercado.ui.features.shopping.ShoppingListViewModel

@Composable
fun MainScren(rootNavController: NavController) {

    val tabsNavController = rememberNavController()

    val sharedShoppingListViewModel: ShoppingListViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = tabsNavController)
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = tabsNavController,
            startDestination = "Search",
        ) {
            composable(route = "Search") {
                SearchScreen(viewModel = sharedShoppingListViewModel)
            }

            composable(route = "Shopping") {
                ShoppingListScreen(
                    viewModel = sharedShoppingListViewModel,
                    onNavigateToSearch = {
                        tabsNavController.navigate(route = "Search")
                    }
                )
            }

            composable(route = "Inform") {
                InformScreen()
            }

            composable(route = "Profile") {
                ProfileScreen(
                    onLogoutClick = {
                        rootNavController.navigate(route = "Login") {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    },
                    onMyProductsClick = {
                        rootNavController.navigate(route = "MyProducts")
                    },
                    onSettingsClick = {
                        rootNavController.navigate(route = "Settings")
                    }
                )
            }
        }
    }
}