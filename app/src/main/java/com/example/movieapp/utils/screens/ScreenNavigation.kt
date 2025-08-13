package com.example.movieapp.utils.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.detail.DetailScreen
import com.example.movieapp.ui.screens.intro.IntroScreen
import com.example.movieapp.ui.screens.home.MainScreenBottomNav
import com.example.movieapp.ui.screens.register.RegisterScreen
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.MOVIE_ID
import com.example.movieapp.utils.SessionManger

@Composable
fun ScreenNavigation(userInfo: SessionManger) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MyScreens.IntroScreen.route) {
        composable(route = MyScreens.IntroScreen.route) {
            IntroScreen(navigation = navController, userInfo)
        }
        composable(route = MyScreens.RegisterScreen.route) {
            RegisterScreen(navController = navController, userInfo = userInfo)
        }
        composable(route = MyScreens.MainScreen.route) {
            MainScreenBottomNav()
        }



    }
}