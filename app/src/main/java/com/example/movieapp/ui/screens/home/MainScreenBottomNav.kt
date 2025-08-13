package com.example.movieapp.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.detail.DetailScreen
import com.example.movieapp.ui.screens.favorite.FavoriteScreen
import com.example.movieapp.ui.screens.profile.ProfileScreen
import com.example.movieapp.ui.screens.search.SearchScreen
import com.example.movieapp.utils.Constants.MOVIE_ID
import com.example.movieapp.utils.screens.BottomNavScreen
import com.example.movieapp.utils.screens.MyScreens

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
        bottomBar = {
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute in bottomNavItems.map { it.route }){

                BottomNavigationBar(navController = bottomNavController, items = bottomNavItems)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Home.route) { HomeScreen(navController = bottomNavController) }
            composable(BottomNavScreen.Search.route) { SearchScreen() }
            composable(BottomNavScreen.Favorites.route) { FavoriteScreen() }
            composable(BottomNavScreen.Profile.route) { ProfileScreen() }
            composable( route = MyScreens.DetailScreen.route + "/" + "{${MOVIE_ID}}",
                arguments = listOf(navArgument(MOVIE_ID) { type = NavType.IntType })) {
                DetailScreen(it.arguments!!.getInt(MOVIE_ID, 0),navController = bottomNavController)
            }


        }
    }
}
