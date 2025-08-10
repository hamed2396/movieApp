package com.example.movieapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.screens.intro.IntroScreen
import com.example.movieapp.ui.screens.main.MainScreen
import com.example.movieapp.ui.screens.register.RegisterScreen

@Composable
fun ScreenNavigation(userInfo: SessionManger) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyScreens.IntroScreen.route) {
        composable(route = MyScreens.IntroScreen.route) {
            IntroScreen(navigation = navController)
        }
        composable(route = MyScreens.RegisterScreen.route) {
            RegisterScreen(navController = navController, userInfo = userInfo)
        }
        composable(route = MyScreens.MainScreen.route) {
            MainScreen(navController = navController)
        }

    }
}