package com.example.movieapp.utils

sealed class MyScreens(val route: String) {
    object IntroScreen : MyScreens("introScreen")
    object RegisterScreen: MyScreens("registerScreen")
    object MainScreen: MyScreens("mainScreen")
}