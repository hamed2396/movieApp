package com.example.movieapp.utils.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavScreen("home", Icons.Default.Home, "Home")
    object Search : BottomNavScreen("Search", Icons.Default.Search, "Search")
    object Favorites : BottomNavScreen("Favorites", Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavScreen("profile", Icons.Default.Person, "Profile")
}
