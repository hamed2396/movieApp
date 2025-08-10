package com.example.movieapp.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.screens.favorite.FavoriteScreen
import com.example.movieapp.ui.screens.profile.ProfileScreen
import com.example.movieapp.ui.screens.search.SearchScreen
import com.example.movieapp.utils.screens.BottomNavScreen

@Composable
fun MainScreenBottomNav() {
    val bottomNavController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Search,
        BottomNavScreen.Favorites,
        BottomNavScreen.Profile
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavController, items = bottomNavItems) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Home.route) { HomeScreen() }
            composable(BottomNavScreen.Search.route) { SearchScreen() }
            composable(BottomNavScreen.Favorites.route) { FavoriteScreen() }
            composable(BottomNavScreen.Profile.route) { ProfileScreen() }

        }
    }
}
