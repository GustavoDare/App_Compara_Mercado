package com.example.comparamercado.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comparamercado.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.nav_search)
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.nav_search)
                )
            },
            selected = true,
            onClick ={
                navController.navigate("Search")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = stringResource(id = R.string.nav_shopping)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.nav_shopping)
                )
            },
            selected = false,
            onClick = {
                navController.navigate("Shopping")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = stringResource(id = R.string.nav_price),
                    tint = Color(0xFF4CAF50),
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.nav_inform)
                )
            },
            selected = false,
            onClick = {
                navController.navigate("Inform")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            icon = {
                Icon (
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.nav_profile)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.nav_profile)
                )
            },
            selected = false,
            onClick = {
                navController.navigate("Profile")
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                unselectedIconColor = Color.Gray
            )
        )
    }
}